package br.com.stoneapp.ui

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stoneapp.databinding.FragmentMainBinding
import br.com.stoneapp.state.ItemUi
import br.com.stoneapp.state.MainUiEvent
import br.com.stoneapp.state.MainUiState
import br.com.stoneapp.ui.vm.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            MainRoute(
                viewModel = viewModel
            )
        }
    }

    @Composable
    private fun MainRoute(viewModel: MainViewModel) {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

        MainScreen(
            state = uiState,
            onEvent = viewModel::onEvent
        )
    }

    /**
     * IMPORTANTE (Checklist do Módulo 2):
     * - Nenhuma lógica de negócio aqui.
     * - UI reage SOMENTE ao estado.
     * - Eventos são apenas "intents" (UDF).
     */
    @Composable
    private fun MainScreen(
        state: MainUiState,
        onEvent: (MainUiEvent) -> Unit
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                MainUiState.Loading -> LoadingView()

                MainUiState.Empty -> EmptyView(
                    onRefresh = { onEvent(MainUiEvent.Refresh) }
                )

                is MainUiState.Error -> ErrorView(
                    message = state.message,
                    onRetry = { onEvent(MainUiEvent.Retry) }
                )

                is MainUiState.Success -> SuccessListView(
                    items = state.items,
                    onRefresh = { onEvent(MainUiEvent.Refresh) }
                )
            }
        }
    }

    @Composable
    private fun LoadingView() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun EmptyView(onRefresh: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Nenhum item para exibir.")
            Spacer(Modifier.height(12.dp))
            Button(onClick = onRefresh) { Text("Recarregar") }
        }
    }

    @Composable
    private fun ErrorView(message: String, onRetry: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Erro: $message")
            Spacer(Modifier.height(12.dp))
            Button(onClick = onRetry) { Text("Tentar de novo") }
        }
    }

    @Composable
    private fun SuccessListView(
        items: List<ItemUi>,
        onRefresh: () -> Unit
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "StoneApp",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = onRefresh) {
                    Text("Atualizar")
                }
            }

            Divider()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = items,
                    key = { it.id } // performance: estabilidade + key
                ) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(16.dp)
                        )
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
