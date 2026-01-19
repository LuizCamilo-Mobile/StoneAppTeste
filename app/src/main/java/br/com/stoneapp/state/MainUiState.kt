package br.com.stoneapp.state

sealed interface MainUiState {
    data object Loading : MainUiState
    data object Empty : MainUiState
    data class Success(val items: List<ItemUi>) : MainUiState
    data class Error(val message: String) : MainUiState
}

data class ItemUi(
    val id: Long,
    val title: String
)