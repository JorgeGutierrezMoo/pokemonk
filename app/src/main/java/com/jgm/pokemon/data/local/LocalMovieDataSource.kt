package com.jgm.pokemon.data.local

import com.jgm.pokemon.data.model.PokemonEntity
import com.jgm.pokemon.data.model.PokemonList
import com.jgm.pokemon.data.model.toPokemonList

class LocalMovieDataSource(private val pokemonDao: PokemonDao) {

    suspend fun getPokemon(): PokemonList{
        return pokemonDao.getAllPokemon().toPokemonList()
    }

    suspend fun savePokemon(pokemon: PokemonEntity){
        pokemonDao.savePokemon(pokemon)
    }
}