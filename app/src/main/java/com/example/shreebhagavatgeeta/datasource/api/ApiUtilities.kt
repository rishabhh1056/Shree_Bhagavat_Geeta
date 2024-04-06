package com.example.shreebhagavatgeeta.datasource.api


import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiUtilities {

    private val headers = mapOf<String,String>(
        "Accept" to "application/json",
        "X-RapidAPI-Key" to  "403f26774amsh2a12ca6c32c4eeep10885fjsnb9d14fe06701",
        "X-RapidAPI-Host" to "bhagavad-gita3.p.rapidapi.com"
    )

    val client = OkHttpClient.Builder().apply {
        addInterceptor {chain->
                val newRequest:Request = chain.request().newBuilder().apply {
                    headers.forEach{key , value -> addHeader(key, value)}
                }.build()

            chain.proceed(newRequest)
        }
    }.build()



    val api : ApiInterface by  lazy {

        Retrofit.Builder()
            .baseUrl("https://bhagavad-gita3.p.rapidapi.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}