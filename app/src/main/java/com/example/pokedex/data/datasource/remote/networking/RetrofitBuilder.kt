package com.example.pokedex.data.datasource.remote.networking

import com.example.pokedex.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun get(): ApiService {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient())
            .build()
        return builder.create(ApiService::class.java)
    }

    private fun getOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        var okhttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original  = chain.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()
                val request = chain.request().newBuilder().url(url).build()
                chain.proceed(request)
            }

        if (BuildConfig.DEBUG){
            okhttpBuilder = okhttpBuilder.addInterceptor(loggingInterceptor)
        }
        return okhttpBuilder.build()
    }

}