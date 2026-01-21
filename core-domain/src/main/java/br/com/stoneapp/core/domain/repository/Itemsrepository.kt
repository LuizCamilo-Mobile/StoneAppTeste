package br.com.stoneapp.core.domain.repository

import br.com.stoneapp.core.domain.model.Item

/**
 * Contrato do domínio.
 * Data implementa. Domain e Feature dependem.
 */
interface ItemsRepository {
    suspend fun fetchItems(): List<Item>
}
