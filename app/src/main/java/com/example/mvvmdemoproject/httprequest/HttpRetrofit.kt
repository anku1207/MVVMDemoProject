package com.example.mvvmdemoproject.httprequest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRetrofit {
   private var apiRequset :APIRequset? =null
   private var retrofit: Retrofit?=null
   val base_URL="https://5e510330f2c0d300147c034c.mockapi.io/"

   fun getRetrofit():APIRequset{
      if(apiRequset==null){
         retrofit =Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         apiRequset=retrofit!!.create(APIRequset::class.java)
      }

      return apiRequset!!
   }
}