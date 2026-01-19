package br.com.stoneapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stoneapp.data.FakeRepository
import br.com.stoneapp.state.MainUiEvent
import br.com.stoneapp.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: FakeRepository = FakeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        // Importante pro Módulo 2:
        // - a tela "nasce" baseada em estado
        // - o trigger inicial é responsabilidade do ViewModel (não do Composable)
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

            runCatching { repository.fetchItems() }
                .onSuccess { items ->
                    _uiState.value =
                        if (items.isEmpty()) MainUiState.Empty
                        else MainUiState.Success(items)
                }
                .onFailure { err ->
                    _uiState.value = MainUiState.Error(err.message ?: "Erro desconhecido")
                }
        }
    }
}
