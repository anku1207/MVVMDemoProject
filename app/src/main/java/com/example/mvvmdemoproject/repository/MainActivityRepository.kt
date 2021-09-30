package com.example.mvvmdemoproject.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemoproject.httprequest.HttpRetrofit
import com.example.mvvmdemoproject.httprequest.Resource
import com.example.mvvmdemoproject.model.ApiUserVO
import okhttp3.RequestBody

class MainActivityRepository {
   suspend fun getUserDetails(): List<ApiUserVO> {
     return HttpRetrofit.getRetrofit().getUsers()
   }

    suspend fun saveMultiImage(body : RequestBody): String {
        return HttpRetrofit.getRetrofit().uploadMultiFile(body)
    }
}