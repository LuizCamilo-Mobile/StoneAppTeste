// FILE: feature-home/src/test/java/br/com/stoneapp/feature/home/vm/MainViewModelTest.kt
package br.com.stoneapp.feature.home.vm

import br.com.stoneapp.core.domain.model.Item
import br.com.stoneapp.core.domain.repository.ItemsRepository
import br.com.stoneapp.core.domain.usecase.GetItemsUseCase
import br.com.stoneapp.feature.home.state.MainUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @Test
    fun `init should reach Success when repository returns items`() = runTest {
        val mainDispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(mainDispatcher)

        try {
            val repo = object : ItemsRepository {
                override suspend fun fetchItems(): List<Item> =
                    listOf(Item(1, "Item 1"), Item(2, "Item 2"))
            }

            val vm = MainViewModel(GetItemsUseCase(repo))

            advanceUntilIdle()

            assertTrue(vm.uiState.value is MainUiState.Success)
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun `init should reach Empty when repository returns empty`() = runTest {
        val mainDispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(mainDispatcher)

        try {
            val repo = object : ItemsRepository {
                override suspend fun fetchItems(): List<Item> = emptyList()
            }

            val vm = MainViewModel(GetItemsUseCase(repo))

            advanceUntilIdle()

            assertTrue(vm.uiState.value is MainUiState.Empty)
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun `init should reach Error when repository throws`() = runTest {
        val mainDispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(mainDispatcher)

        try {
            val repo = object : ItemsRepository {
                override suspend fun fetchItems(): List<Item> = error("boom")
            }

            val vm = MainViewModel(GetItemsUseCase(repo))

            advanceUntilIdle()

            assertTrue(vm.uiState.value is MainUiState.Error)
        } finally {
            Dispatchers.resetMain()
        }
    }
}
