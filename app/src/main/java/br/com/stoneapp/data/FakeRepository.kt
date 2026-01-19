package br.com.stoneapp.data

import br.com.stoneapp.state.ItemUi
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeRepository {

    /**
     * Simula:
     * - 70% sucesso com lista
     * - 15% sucesso vazio
     * - 15% erro
     */
    suspend fun fetchItems(): List<ItemUi> {
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
            ItemUi(
                id = now + index,
                title = "Item #${index + 1} (ts=$now)"
            )
        }
    }
}
