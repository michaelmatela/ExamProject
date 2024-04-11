package com.example.examproject.features.dashboard.repository

import android.content.Context
import androidx.room.Room
import com.example.examproject.data.AppDatabase
import com.example.examproject.data.entity.ActivityEntity
import com.example.examproject.data.entity.DashboardEntity
import com.example.examproject.data.entity.DetailsEntity
import com.example.examproject.data.entity.FileEntity
import com.example.examproject.data.entity.IconEntity
import com.example.examproject.data.entity.LevelEntity
import com.example.examproject.data.entity.LockedIconEntity
import com.example.examproject.features.dashboard.model.DashboardModel
import com.example.examproject.features.dashboard.service.DashboardService
import kotlinx.coroutines.flow.MutableStateFlow

class DashboardRepository(private val context: Context) {
    private val dashboardService = DashboardService(context)

    val allLevelsResponseFlow = MutableStateFlow<DashboardEntity?>(null)

    suspend fun fetchData() {
        val database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
        val dashboardDao = database.dashboardDao()
        val dashboardModel: DashboardModel = dashboardService.fetchDashboardModelFromJsonFile()

        val dashboardEntity = DashboardEntity(
            levels = dashboardModel.levels.map { level ->
                LevelEntity(
                    level = level.level,
                    title = level.title,
                    description = level.description,
                    state = level.state,
                    activities = level.activities.map { activity ->
                        ActivityEntity(
                            id = activity.id,
                            challengeId = activity.challengeId,
                            type = activity.type,
                            title = activity.title,
                            titleB = activity.titleB,
                            description = activity.description,
                            descriptionB = activity.descriptionB,
                            state = activity.state,
                            icon = activity.icon?.let { icon ->
                                IconEntity(
                                    file = icon.file?.let { file ->
                                        FileEntity(
                                            url = file.url,
                                            details = file.details?.let { details ->
                                                DetailsEntity(
                                                    size = details.size
                                                )
                                            },
                                            fileName = file.fileName,
                                            contentType = file.contentType
                                        )
                                    },
                                    title = icon.title,
                                    description = icon.description
                                )
                            },
                            lockedIcon = activity.lockedIcon?.let { lockedIcon ->
                                LockedIconEntity(
                                    file = lockedIcon.file?.let { file ->
                                        FileEntity(
                                            url = file.url,
                                            details = file.details?.let { details ->
                                                DetailsEntity(
                                                    size = details.size
                                                )
                                            },
                                            fileName = file.fileName,
                                            contentType = file.contentType
                                        )
                                    },
                                    title = lockedIcon.title,
                                    description = lockedIcon.description
                                )
                            }
                        )
                    }
                )
            }
        )

        // Insert Room entities into the database using DAO
        dashboardDao.insertDashboard(dashboardEntity)
        allLevelsResponseFlow.value = dashboardDao.getAllLevels().first()
    }
}