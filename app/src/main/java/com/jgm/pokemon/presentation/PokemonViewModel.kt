package com.jgm.pokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.jgm.pokemon.core.Resource
import com.jgm.pokemon.repository.pokemon.PokemonRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PokemonViewModel(private val repo: PokemonRepository) : ViewModel() {

    fun fetchPokemon() = liveData(Dispatchers.IO) {

        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getPokemon()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}

class PokemonViewModelFactory(
    private val repo: PokemonRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PokemonRepository::class.java).newInstance(repo)
    }
}