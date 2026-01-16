package br.com.stoneapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stoneapp.data.FakeRepository
import br.com.stoneapp.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel (
    private val repository: FakeRepository = FakeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun load(){
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            runCatching {
                repository.fetch()
            }.onSuccess { data ->
                _uiState.value = MainUiState.Success(data)
            }.onFailure { err ->
                _uiState.value = MainUiState.Error(err.message ?: "Erro desconhecido")

            }
        }
    }

}