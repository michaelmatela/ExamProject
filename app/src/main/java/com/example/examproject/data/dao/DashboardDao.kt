package com.example.examproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examproject.data.entity.DashboardEntity

@Dao
interface DashboardDao {

    @Query("SELECT * FROM dashboard")
    suspend fun getAllLevels(): List<DashboardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDashboard(dashboard: DashboardEntity)

}