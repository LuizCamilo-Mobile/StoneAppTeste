package br.com.stoneapp.di

import br.com.stoneapp.core.data.FakeItemsRepository
import br.com.stoneapp.core.domain.usecase.GetItemsUseCase

class AppContainer {

    private val itemsRepository = FakeItemsRepository()

    val getItemsUseCase = GetItemsUseCase(
        repository = itemsRepository
    )
}
