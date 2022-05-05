package com.okankocakose.appcentproject1.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    private val BASE_URL = "https://api.rawg.io/api/"
    var myApiInterface : ApiInterface


    companion object{
        private var instance : ApiClient? = null
        fun getInstance()= synchronized(this){
            if(instance==null){
                instance = ApiClient()
            }
            instance

        }
    }

    init {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        myApiInterface = retrofitBuilder.create(ApiInterface::class.java)

    }


}