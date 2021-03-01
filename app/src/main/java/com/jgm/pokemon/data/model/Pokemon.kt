package com.jgm.pokemon.data.model

import androidx.room.Entity
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

    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""

)

//dentro de la url el elemento 34 representa el id
//este id
/*
@GET("pokemon/")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<NamedApiResourceList>
 */

// "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
//  https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png
//Este lo del final es el id del pokemon

//https://pokeapi.co/api/v2/pokemon/{id or name}/ solo get

//regresa una página de 20 por default
//https://pokeapi.co/api/v2/{endpoint}/
/*
    val id: Int = -1,
    val name: String = "",
    val base_expedience: Int = -1,
    val height: Int = -1,
    val is_default: Boolean = false,
    val order: Int = -1,
    val weight: Int = -1

 */