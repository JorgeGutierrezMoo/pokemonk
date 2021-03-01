package com.jgm.pokemon.repository.pokemon

import com.jgm.pokemon.data.model.PokemonList
import com.jgm.pokemon.data.remote.PokemonDataSource
import com.jgm.pokemon.repository.pokemon.PokemonRepository

class PokemonRepositoryImplements(private val dataSource: PokemonDataSource): PokemonRepository {

    override suspend fun getPokemon(): PokemonList = dataSource.getPokemon()

}