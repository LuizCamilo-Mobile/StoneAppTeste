package br.com.stoneapp.state

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val data: String) : MainUiState
    data class Error(val message: String) : MainUiState
}