package br.com.stoneapp.state

sealed interface MainUiEvent {
    data object Retry : MainUiEvent
    data object Refresh : MainUiEvent
}
