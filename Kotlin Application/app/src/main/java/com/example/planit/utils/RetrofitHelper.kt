package com.example.planit.utils

import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper : AppCompatActivity() {
    private const val baseUrl: String = "http://10.0.2.2:5066/api/v1/"

    private lateinit var session : Session
    private lateinit var token : String

    fun create(): Retrofit {
        session =  Session()
        token = session.private().toString()

        if(token.compareTo("") == 0){
            session =  Session()
            session.private().toString()
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                chain.proceed(request)
            }.build())
            .build()
    }
}