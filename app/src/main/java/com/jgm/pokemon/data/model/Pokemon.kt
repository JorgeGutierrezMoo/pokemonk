package com.jgm.pokemon.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Pokemon(

    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""

)

data class PokemonList(
    @SerializedName("results")
    val pokemonResultsList: List<Pokemon> = listOf()
)

//Room
@Entity
data class PokemonEntity(

    @PrimaryKey
    val name: String = "",
    @ColumnInfo(name = "url")
    val url: String = ""

)

fun List<PokemonEntity>.toPokemonList(): PokemonList{

    val resultList = mutableListOf<Pokemon>()
    this.forEach{ pokemonEntity ->
        resultList.add(pokemonEntity.toPokemon())
    }
    return PokemonList(resultList)
}

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(
    this.name,
    this.url
)

fun Pokemon.toPokemonEntity(): PokemonEntity = PokemonEntity(
    this.name,
    this.url
)