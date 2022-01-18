package com.example.mvvmdemoproject

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.adapter.MainActivityAdapter
import com.example.mvvmdemoproject.httprequest.Status
import com.example.mvvmdemoproject.viewmodel.MainActivityViewModel
import kotlin.collections.ArrayList
import okhttp3.ResponseBody

import android.widget.Toast
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull


import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


import android.os.FileUtils
import androidx.annotation.NonNull
import okhttp3.MultipartBody
import java.net.URI.create

import okhttp3.RequestBody
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var rview: RecyclerView
    var dataList: ArrayList<String> = ArrayList();
    lateinit var mainActivityAdapter: MainActivityAdapter
    lateinit var searchview: SearchView
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var process: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rview = findViewById(R.id.rview)
        searchview = findViewById(R.id.searchview)
        process = findViewById(R.id.process)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        mainActivityAdapter = MainActivityAdapter(this, dataList)
        rview.layoutManager = LinearLayoutManager(this)
        rview.adapter = mainActivityAdapter

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null && !p0.equals("")) {
                    val temList = ArrayList<String>()
                    for (i in dataList) {
                        if (i.lowercase().contains(p0.lowercase())) {
                            temList.add(i)

                        }
                    }
                    mainActivityAdapter.filterList(temList)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 == null || p0.equals("")) {
                    mainActivityAdapter.filterList(dataList)
                }
                return false
            }
        })


        //multi image upload
      //  uploadMultiFile(applicationContext)



        mainActivityViewModel.getAllUser().observe(this, Observer {
            // without coroutines
            /* for(i in it!!){
                val jsonObject =i
                dataList.add(jsonObject.name)

            }
            mainActivityAdapter.notifyDataSetChanged()*/

            // with coroutines

            when (it.status) {
                Status.SUCCESS -> {
                    process.visibility = View.GONE

                    val JsonArray = it.data
                    for (i in JsonArray!!) {
                        val jsonObject = i
                        dataList.add(jsonObject.name)

                    }
                    mainActivityAdapter.notifyDataSetChanged()
                }
                Status.LOADING -> {
                    process.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    process.visibility = View.GONE

                }
            }
        })

        Log.w("status", "complete main activity")


        // validaTextField (""){ a: Int, b: Int -> a + b }
    }


    companion object {
        /*fun validaTextField(value: String,name: (Int, Int) -> Unit){
            name(500,4)
        }*/
        fun validaTextField(name: String, age: Int): Boolean {
            return !(name == "" || age == 0)
        }
    }


    private fun uploadMultiFile(context: Context) {
        val filePaths: ArrayList<String> = ArrayList()
        filePaths.add("/storage/emulated/0/DCIM/MobiKwik20200108_0822108346669284420065020.jpg")
        filePaths.add("/storage/emulated/0/DCIM/MobiKwik20200108_0822108346669284420065020.jpg")
        filePaths.add("/storage/emulated/0/DCIM/MobiKwik20200108_0822108346669284420065020.jpg")

        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart("user_name", "Robert")
        builder.addFormDataPart("email", "mobile.apps.pro.vn@gmail.com")
        // Map is used to multipart the file using okhttp3.RequestBody
        // Multiple Images
        try {

            for (i in 0 until filePaths.size) {
                val file = File(filePaths[i])
                builder.addFormDataPart(
                    "file[]",
                    file.getName(),
                    file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                )
            }
            val requestBody = builder.build()
            mainActivityViewModel.saveMultiImage(requestBody).observe(this, Observer {
                Toast.makeText(context, "" + it.status, Toast.LENGTH_SHORT).show()
                Log.w("Response", it.data.toString());
                Log.w("Errro11111111 ", it.message.toString())
                when (it.status) {

                }
            })

        } catch (e: Exception) {
            Log.w("Errro ", e.message.toString())
        }


    }


}
