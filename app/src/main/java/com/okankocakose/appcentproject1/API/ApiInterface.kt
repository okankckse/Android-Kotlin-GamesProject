package com.okankocakose.appcentproject1.API

import com.okankocakose.appcentproject1.Model.GamesDataModel
import com.okankocakose.appcentproject1.Model.GamesDetailDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("games")
    fun getGames(@Query("key") key : String  ) : Call<GamesDataModel>

    @GET("games/{id}")
    fun getGamesDetail(@Path("id") id : Int, @Query("key") key: String) : Call<GamesDetailDataModel>

}