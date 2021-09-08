package com.example.mvvmdemoproject.httprequest

import com.example.mvvmdemoproject.model.ApiUserVO
import retrofit2.Call
import retrofit2.http.GET

interface APIRequset {

   /* @GET("users")
    fun getUsers(): Call<List<ApiUserVO>>*/

    @GET("users")
    suspend fun getUsers(): List<ApiUserVO>
}