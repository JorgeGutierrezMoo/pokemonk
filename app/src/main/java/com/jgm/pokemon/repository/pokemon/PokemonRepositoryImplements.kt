package com.jgm.pokemon.repository.pokemon

import com.jgm.pokemon.data.model.PokemonList
import com.jgm.pokemon.data.remote.RemotePokemonDataSource

class PokemonRepositoryImplements(private val dataSourceRemote: RemotePokemonDataSource): PokemonRepository {

    override suspend fun getPokemon(): PokemonList = dataSourceRemote.getPokemon()

}