package com.example.valorantagentsguide

import com.example.valorantagentsguide.data.remote.response.AbilitiesItem

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromAbilityList(abilities: List<AbilitiesItem>): String{
        return Gson().toJson(abilities)
    }
    @TypeConverter
    fun toAbilityList(abilitiesString: String): List<AbilitiesItem>{
        val listType = object : TypeToken<List<AbilitiesItem>>() {}.type
        return Gson().fromJson(abilitiesString, listType)
    }
}