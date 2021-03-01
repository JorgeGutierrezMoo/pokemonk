package com.jgm.pokemon.repository.pokemon

import com.jgm.pokemon.data.local.LocalPokemonDataSource
import com.jgm.pokemon.data.model.PokemonList
import com.jgm.pokemon.data.model.toPokemonEntity
import com.jgm.pokemon.data.remote.RemotePokemonDataSource

class PokemonRepositoryImplements(
    private val dataSourceRemote: RemotePokemonDataSource,
    private val dataSourceLocal: LocalPokemonDataSource
) : PokemonRepository {

    override suspend fun getPokemon(): PokemonList {
        dataSourceRemote.getPokemon().pokemonResultsList.forEach{ pokemon ->
            dataSourceLocal.savePokemon(pokemon.toPokemonEntity())
        }
        return dataSourceLocal.getPokemonLocalDataSource()
    }

}