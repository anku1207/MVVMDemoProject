package com.example.mvvmdemoproject.httprequest

import com.example.mvvmdemoproject.model.ApiUserVO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

import retrofit2.http.POST

import retrofit2.http.Multipart




interface APIRequset {

   /* @GET("users")
    fun getUsers(): Call<List<ApiUserVO>>*/

    @GET("users")
    suspend fun getUsers(): List<ApiUserVO>

    @POST("uploadDocument.php")
    suspend fun uploadMultiFile(@Body  body :RequestBody):ResponseBody

}