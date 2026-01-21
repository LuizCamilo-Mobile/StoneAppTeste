package br.com.stoneapp.core.domain.usecase

import br.com.stoneapp.core.domain.model.Item
import br.com.stoneapp.core.domain.repository.ItemsRepository

/**
 * UseCase centraliza regras.
 * Hoje só delega, amanhã valida/ordena/filtra.
 */
class GetItemsUseCase(
    private val repository: ItemsRepository
) {
    suspend operator fun invoke(): List<Item> {
        return repository.fetchItems()
    }
}
