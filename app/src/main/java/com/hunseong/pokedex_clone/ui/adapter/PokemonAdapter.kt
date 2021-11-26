package com.hunseong.pokedex_clone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.hunseong.pokedex_clone.databinding.ItemPokemonBinding
import com.hunseong.pokedex_clone.model.Pokemon

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(diffUtil) {

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                //todo setOnClickListener
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
                Toast.makeText(binding.root.context, getItem(position).name, Toast.LENGTH_SHORT).show()
            }
        }

        fun bindPokemon(pokemon: Pokemon) {
            binding.pokemon = pokemon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindPokemon(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }

        }
    }
}