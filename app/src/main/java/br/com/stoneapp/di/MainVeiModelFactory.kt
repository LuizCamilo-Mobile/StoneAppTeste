package br.com.stoneapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.stoneapp.core.domain.usecase.GetItemsUseCase
import br.com.stoneapp.feature.home.vm.MainViewModel

class MainViewModelFactory(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getItemsUseCase) as T
        }
        error("Unknown ViewModel class: $modelClass")
    }
}
