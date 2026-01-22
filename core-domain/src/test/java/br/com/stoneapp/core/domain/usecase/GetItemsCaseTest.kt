package br.com.stoneapp.core.domain.usecase

import br.com.stoneapp.core.domain.model.Item
import br.com.stoneapp.core.domain.repository.ItemsRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class GetItemsUseCaseTest {

    @Test
    fun `invoke should return items from repository`() = kotlinx.coroutines.runBlocking {
        val fakeRepo = object : ItemsRepository {
            override suspend fun fetchItems(): List<Item> {
                return listOf(
                    Item(id = 1, title = "A"),
                    Item(id = 2, title = "B")
                )
            }
        }

        val useCase = GetItemsUseCase(fakeRepo)

        val result = useCase()

        assertEquals(2, result.size)
        assertEquals("A", result[0].title)
        assertEquals("B", result[1].title)
    }
}
