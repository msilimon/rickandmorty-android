package com.msilimon.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.msilimon.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.rvCharacters.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        charactersAdapter = CharactersAdapter()
        binding.rvCharacters.adapter =
            charactersAdapter.withLoadStateFooter(CustomLoadStateAdapter())

        charactersAdapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.INVISIBLE
                }
                LoadState.Loading -> {
                    binding.rvCharacters.visibility = View.VISIBLE
                    binding.btRefresh.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        (loadState.refresh as LoadState.Error).error.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    binding.rvCharacters.visibility = View.INVISIBLE
                    binding.btRefresh.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }

        binding.btRefresh.setOnClickListener {
            charactersAdapter.refresh()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.pagedCharacters.onEach {
                charactersAdapter.submitData(it)
            }.launchIn(this)
        }
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}