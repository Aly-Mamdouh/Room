package com.ali.roomrevision

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun convertToString(user: User):String{
        return Gson().toJson(user)
    }
    @TypeConverter
    fun convertToUser(str: String):User{
        return Gson().fromJson(str,User::class.java)
    }
}