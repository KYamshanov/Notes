package ru.undframe.notes.data

import com.google.gson.annotations.SerializedName

data class AliveToken(
    @SerializedName("status")
    val status:Int,
    @SerializedName("user_id")
    val userId:String
)