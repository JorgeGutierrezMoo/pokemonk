package com.jgm.pokemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jgm.pokemon.data.model.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: PokemonEntity)

}