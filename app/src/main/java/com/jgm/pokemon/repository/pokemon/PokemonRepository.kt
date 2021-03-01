package com.jgm.pokemon.repository.pokemon

import com.jgm.pokemon.data.model.PokemonList

interface PokemonRepository {

    suspend fun getPokemon(): PokemonList

}