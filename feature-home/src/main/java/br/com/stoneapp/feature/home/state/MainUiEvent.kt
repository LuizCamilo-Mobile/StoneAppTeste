package br.com.stoneapp.feature.home.state

sealed interface MainUiEvent {
    data object Retry : MainUiEvent
    data object Refresh : MainUiEvent
}
