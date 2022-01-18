package com.example.mvvmdemoproject

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.adapter.MainActivityAdapter
import com.example.mvvmdemoproject.httprequest.Status
import com.example.mvvmdemoproject.viewmodel.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var rview : RecyclerView
    var dataList :ArrayList<String> = ArrayList();
    lateinit var mainActivityAdapter :MainActivityAdapter
    lateinit var searchview :SearchView
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var process : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rview=findViewById(R.id.rview)
        searchview=findViewById(R.id.searchview)
        process=findViewById(R.id.process)

        mainActivityViewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)


        mainActivityAdapter = MainActivityAdapter(this,dataList)
        rview.layoutManager=LinearLayoutManager(this)
        rview.adapter=mainActivityAdapter

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0!=null && !p0.equals("")){
                    val temList = ArrayList<String>()
                    for (i in dataList){
                        if(i.lowercase().contains(p0.lowercase())){
                            temList.add(i)

                        }
                    }
                    mainActivityAdapter.filterList(temList)
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0==null || p0.equals("")){
                    mainActivityAdapter.filterList(dataList)
                }
                return false
            }
        })


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
                    process.visibility=View.GONE

                    val JsonArray =it.data
                    for(i in JsonArray!!){
                        val jsonObject =i
                        dataList.add(jsonObject.name)

                    }
                    mainActivityAdapter.notifyDataSetChanged()
                }
                Status.LOADING -> {
                    process.visibility=View.VISIBLE
                }
                Status.ERROR -> {
                    process.visibility=View.GONE
                   
                }
            }
        })
    }
}