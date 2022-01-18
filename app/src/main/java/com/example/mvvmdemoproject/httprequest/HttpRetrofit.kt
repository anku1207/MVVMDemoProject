package com.example.mvvmdemoproject.httprequest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.ConnectionSpec
import android.os.Build
import java.util.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object HttpRetrofit {
   private var apiRequset :APIRequset? =null
   private var retrofit: Retrofit?=null
   val base_URL="https://5e510330f2c0d300147c034c.mockapi.io/"
  //val base_URL= "http://144.48.78.35/Json_API/EzeeOffice/"



   fun getRetrofit():APIRequset{

      val interceptor = HttpLoggingInterceptor()
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

      if(apiRequset==null){
         retrofit =Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
         apiRequset=retrofit!!.create(APIRequset::class.java)
      }

      return apiRequset!!
   }
}