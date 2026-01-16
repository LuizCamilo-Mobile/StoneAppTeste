package br.com.stoneapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.stoneapp.databinding.FragmentMainBinding
import br.com.stoneapp.state.MainUiState
import br.com.stoneapp.ui.vm.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retry.setOnClickListener { viewModel.load() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    render(state)
                }
            }
        }

        if (savedInstanceState == null) {
            viewModel.load()
        }
    }

    private fun render(state: MainUiState) {
        when (state) {
            MainUiState.Loading -> {
                binding.progress.visibility = View.VISIBLE
                binding.retry.visibility = View.GONE
                binding.message.text = "Carregando..."
            }
            is MainUiState.Success -> {
                binding.progress.visibility = View.GONE
                binding.retry.visibility = View.GONE
                binding.message.text = state.data
            }
            is MainUiState.Error -> {
                binding.progress.visibility = View.GONE
                binding.retry.visibility = View.VISIBLE
                binding.message.text = "Erro: ${state.message}"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}