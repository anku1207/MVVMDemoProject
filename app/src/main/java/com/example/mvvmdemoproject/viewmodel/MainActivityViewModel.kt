package com.example.mvvmdemoproject.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mvvmdemoproject.httprequest.HttpRetrofit
import com.example.mvvmdemoproject.httprequest.Resource
import com.example.mvvmdemoproject.model.ApiUserVO
import com.example.mvvmdemoproject.repository.MainActivityRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    /*fun getAllUser():LiveData<List<ApiUserVO>>{
        val data = MutableLiveData<List<ApiUserVO>>()
        val fetchData=HttpRetrofit.getRetrofit().getUsers()
        fetchData.enqueue(object : Callback<List<ApiUserVO>>{
            override fun onResponse(call: Call<List<ApiUserVO>>,response: Response<List<ApiUserVO>>
            ) {
                data.postValue(response.body())
            }
            override fun onFailure(call: Call<List<ApiUserVO>>, t: Throwable) {
               Log.w("error",t.message.toString())
            }
        })
        return data
    }*/


    fun getAllUser():LiveData<Resource<List<ApiUserVO>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val data = MainActivityRepository().getUserDetails()
            emit(Resource.success(data))
        }catch (e :Exception){
            emit(Resource.error(e.message.toString(),null))
        }
    }
}