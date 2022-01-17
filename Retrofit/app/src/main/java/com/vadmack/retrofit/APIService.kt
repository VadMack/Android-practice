package com.vadmack.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    //@GET("https://anapioficeandfire.com/api/characters/{id}")
    @GET("characters/{id}")
    fun findCharacterById(@Path("id") id : Int) : Call<Person>
}