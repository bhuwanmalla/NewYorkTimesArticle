package bhuwan.example.newyorktimes.constants

import bhuwan.example.newyorktimes.api_service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(UrlConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}