package br.com.stoneapp.core.data

import br.com.stoneapp.core.domain.model.Item
import br.com.stoneapp.core.domain.repository.ItemsRepository
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Implementação Data do contrato do Domain.
 * Hoje é Fake. Amanhã pode virar Retrofit/Room mantendo a mesma interface.
 */
class FakeItemsRepository : ItemsRepository {

    /**
     * Simula:
     * - 70% sucesso com lista
     * - 15% sucesso vazio
     * - 15% erro
     */
    override suspend fun fetchItems(): List<Item> {
        delay(1500)

        val roll = Random.nextInt(100)

        if (roll < 15) {
            error("Falha simulada: tente novamente")
        }

        if (roll < 30) {
            return emptyList()
        }

        val now = System.currentTimeMillis()
        val size = Random.nextInt(from = 5, until = 15)

        return List(size) { index ->
            Item(
                id = now + index,
                title = "Item #${index + 1} (ts=$now)"
            )
        }
    }
}
