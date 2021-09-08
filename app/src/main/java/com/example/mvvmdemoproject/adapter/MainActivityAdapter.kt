package com.example.mvvmdemoproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R

class MainActivityAdapter(var context: Context ,var  dataList : ArrayList<String>) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview =itemView.findViewById<TextView>(R.id.textview)
        var mainlayout=itemView.findViewById<LinearLayout>(R.id.mainlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_rview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPosition = position
        holder.textview.text= dataList[itemPosition]

        holder.mainlayout.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(context, dataList[itemPosition],Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun getItemCount(): Int {
       return dataList.size
    }
}