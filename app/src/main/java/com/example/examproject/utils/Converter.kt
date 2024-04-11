package com.example.examproject.utils
import androidx.room.TypeConverter
import com.example.examproject.data.entity.LevelEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromLevelsList(levels: List<LevelEntity>): String {
        return Gson().toJson(levels)
    }

    @TypeConverter
    fun toLevelsList(levelsString: String): List<LevelEntity> {
        val listType = object : TypeToken<List<LevelEntity>>() {}.type
        return Gson().fromJson(levelsString, listType)
    }
}