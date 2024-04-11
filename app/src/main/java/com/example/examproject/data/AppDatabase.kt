package com.example.examproject.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examproject.data.dao.DashboardDao
import com.example.examproject.data.entity.DashboardEntity
import com.example.examproject.utils.Converter

@Database(entities = [DashboardEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dashboardDao(): DashboardDao
}