package com.example.mvvmdemoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.adapter.MainActivityAdapter

class MainActivity : AppCompatActivity() {
    lateinit var rview : RecyclerView
    var dataList :ArrayList<String> = ArrayList();
    lateinit var mainActivityAdapter :MainActivityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rview=findViewById(R.id.rview)

        dataList.add("manoj")
        dataList.add("shakya")
        dataList.add("anku")
        dataList.add("saxena")
        dataList.add("vishal")
        dataList.add("aman")

        mainActivityAdapter = MainActivityAdapter(this,dataList)
        rview.layoutManager=LinearLayoutManager(this)
        rview.adapter=mainActivityAdapter


    }
}