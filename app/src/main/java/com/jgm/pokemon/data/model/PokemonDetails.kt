package com.jgm.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(

    @SerializedName("id")
    val id: Int = 0,
    //@SerializedName("types")
    //val types: List<TypesItem>? = null,
    @SerializedName("weight")
    val weight: Float = 0f,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("order")
    val order: Int = 0

)


