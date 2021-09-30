package com.example.mvvmdemoproject.httprequest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpRetrofit {
   private var apiRequset :APIRequset? =null
   private var retrofit: Retrofit?=null
   val base_URL="https://5e510330f2c0d300147c034c.mockapi.io/"


   val interceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY

   }
   val client: OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(interceptor)
      .connectTimeout(100, TimeUnit.SECONDS)
      .readTimeout(100, TimeUnit.SECONDS)
      .build()
   fun getRetrofit():APIRequset{
      if(apiRequset==null){
         retrofit =Retrofit.Builder()
            .baseUrl(base_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         apiRequset=retrofit!!.create(APIRequset::class.java)
      }

      return apiRequset!!
   }
}