package com.example.mvvmdemoproject.model
import com.google.gson.annotations.SerializedName

data class ApiUserVO(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("avatar")
    val avatar: String = ""
)
