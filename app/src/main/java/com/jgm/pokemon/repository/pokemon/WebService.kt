package com.jgm.pokemon.repository

import com.google.gson.GsonBuilder
import com.jgm.pokemon.application.AppConstants
import com.jgm.pokemon.data.model.PokemonDetails
import com.jgm.pokemon.data.model.PokemonList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    //https://pokeapi.co/api/v2/
    @GET("pokemon/?limit=60")
    suspend fun getPokemon(
        //@Query("offset") page: Int,
        //@Query("limit") pageSize: Int
    ): PokemonList

    @GET("pokemon/{id}/")
    fun getPokemonInfo(
        @Path("id") id: Int
    ): Call<PokemonDetails>

}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}