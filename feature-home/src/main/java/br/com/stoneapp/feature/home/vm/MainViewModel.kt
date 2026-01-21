package br.com.stoneapp.feature.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stoneapp.core.domain.usecase.GetItemsUseCase
import br.com.stoneapp.feature.home.state.ItemUi
import br.com.stoneapp.feature.home.state.MainUiEvent
import br.com.stoneapp.feature.home.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun onEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.Retry -> load()
            MainUiEvent.Refresh -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading

            runCatching { getItemsUseCase() }
                .onSuccess { items ->
                    val uiItems = items.map { ItemUi(id = it.id, title = it.title) }
                    _uiState.value =
                        if (uiItems.isEmpty()) MainUiState.Empty
                        else MainUiState.Success(uiItems)
                }
                .onFailure { err ->
                    _uiState.value = MainUiState.Error(err.message ?: "Erro desconhecido")
                }
        }
    }
}
