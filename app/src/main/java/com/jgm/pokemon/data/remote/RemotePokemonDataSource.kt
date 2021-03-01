package com.jgm.pokemon.data.remote

import com.jgm.pokemon.data.model.PokemonList
import com.jgm.pokemon.repository.WebService

class RemotePokemonDataSource(private val webService: WebService) {

    suspend fun getPokemon(): PokemonList {
        return webService.getPokemon()
    }

}