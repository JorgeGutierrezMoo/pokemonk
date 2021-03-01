package com.jgm.pokemon.ui.pokemon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jgm.pokemon.core.BaseViewHolder
import com.jgm.pokemon.data.model.Pokemon
import com.jgm.pokemon.databinding.ItemPokemonBinding

class PokemonFragmentAdapter(
    private val pokemonList: List<Pokemon>,
    private val itemClickListener: OnPokemonClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface OnPokemonClickListener {
        fun onPokemonClick(pokemon: Pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PokemonViewHolder(itemBinding)

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PokemonViewHolder -> holder.bind(pokemonList[position])
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    private inner class PokemonViewHolder(val binding: ItemPokemonBinding) :
        BaseViewHolder<Pokemon>(binding.root) {
        override fun bind(item: Pokemon) {
            binding.txtPokemonName.text = item.name
            binding.root.setOnClickListener { itemClickListener.onPokemonClick(item) }
        }
    }

}