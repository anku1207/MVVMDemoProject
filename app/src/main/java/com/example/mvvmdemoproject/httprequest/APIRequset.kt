package com.example.mvvmdemoproject.httprequest

import com.example.mvvmdemoproject.model.ApiUserVO
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIRequset {

   /* @GET("users")
    fun getUsers(): Call<List<ApiUserVO>>*/

    @GET("users")
    suspend fun getUsers(): List<ApiUserVO>

    @POST("/upload_multi_files/MultiPartUpload.php")
    suspend fun uploadMultiFile(@Body  file : RequestBody):String
}