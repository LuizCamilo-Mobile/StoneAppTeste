package br.com.stoneapp.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stoneapp.feature.home.databinding.FragmentMainBinding
import br.com.stoneapp.feature.home.state.ItemUi
import br.com.stoneapp.feature.home.state.MainUiEvent
import br.com.stoneapp.feature.home.state.MainUiState
import br.com.stoneapp.feature.home.vm.MainViewModel

class MainFragment(
    private val factory: ViewModelProvider.Factory
) : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            val state = viewModel.uiState.collectAsStateWithLifecycle().value
            MainScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }

    @Composable
    private fun MainScreen(
        state: MainUiState,
        onEvent: (MainUiEvent) -> Unit
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                MainUiState.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }

                MainUiState.Empty -> Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Nenhum item para exibir.")
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { onEvent(MainUiEvent.Refresh) }) { Text("Recarregar") }
                }

                is MainUiState.Error -> Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Erro: ${state.message}")
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { onEvent(MainUiEvent.Retry) }) { Text("Tentar de novo") }
                }

                is MainUiState.Success -> SuccessListView(
                    items = state.items,
                    onRefresh = { onEvent(MainUiEvent.Refresh) }
                )
            }
        }
    }

    @Composable
    private fun SuccessListView(
        items: List<ItemUi>,
        onRefresh: () -> Unit
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "StoneApp",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = onRefresh) { Text("Atualizar") }
            }

            Divider()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = items, key = { it.id }) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(item.title, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
