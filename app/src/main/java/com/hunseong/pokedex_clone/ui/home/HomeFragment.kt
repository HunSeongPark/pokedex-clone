package com.hunseong.pokedex_clone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hunseong.pokedex_clone.databinding.FragmentHomeBinding
import com.hunseong.pokedex_clone.ui.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@HomeFragment

            adapter = PokemonAdapter { pokemon ->
                val direction = HomeFragmentDirections.homeFragmentToDetailFragment(pokemon)
                findNavController().navigate(direction)
            }
        }

        viewModel.fetchNextPokemonList()
        return binding.root
    }
}