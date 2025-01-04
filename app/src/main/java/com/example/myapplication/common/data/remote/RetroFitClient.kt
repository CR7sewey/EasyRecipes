package com.example.myapplication.common.data.remote

import com.example.myapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    private val httpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            val token = BuildConfig.API_KEY

            clientBuilder.addInterceptor { it ->
                val original: Request = it.request()
                val requestBuilder: Request.Builder = original.newBuilder()
                val originalHttpURL = it.request().url
                println("AQUUUUieieie")
                val newURL = originalHttpURL.newBuilder().addQueryParameter("apiKey", token).build()
                println("AQUUUU")
                val request: Request = requestBuilder.url(newURL).build()
                println(request.url)
                it.proceed(request)
            }
            return  clientBuilder.build()
        }

    private const val BASE_URL = "https://api.spoonacular.com/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()) // from text to kotlin list4
            .build()
    }

}