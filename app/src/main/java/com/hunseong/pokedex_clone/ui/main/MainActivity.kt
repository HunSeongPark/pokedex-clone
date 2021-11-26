package com.hunseong.pokedex_clone.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.pokedex_clone.R
import com.hunseong.pokedex_clone.databinding.ActivityMainBinding
import com.hunseong.pokedex_clone.ui.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@MainActivity
            adapter = PokemonAdapter()
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val isScrollEnabled = layoutManager.findLastVisibleItemPosition() + 1 < layoutManager.itemCount
                    if(!isScrollEnabled && !viewModel.isLoading.value!!) {
                        viewModel.fetchNextPokemonList()
                    }
                }
            })
        }

        viewModel.fetchNextPokemonList()
    }
}