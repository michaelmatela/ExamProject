package com.example.examproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dashboard")
data class DashboardEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("levels")
    val levels: List<LevelEntity>
)

@Entity(tableName = "levels")
data class LevelEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("level")
    val level: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("activities")
    val activities: List<ActivityEntity>
)

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("id")
    val id: String?,
    @SerializedName("challengeId")
    val challengeId: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("titleB")
    val titleB: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("descriptionB")
    val descriptionB: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("icon")
    val icon: IconEntity?,
    @SerializedName("lockedIcon")
    val lockedIcon: LockedIconEntity?
)

@Entity(tableName = "icon")
data class IconEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("file")
    val file: FileEntity?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?
)

@Entity(tableName = "locked_icon")
data class LockedIconEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("file")
    val file: FileEntity?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?
)

@Entity(tableName = "file")
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("url")
    val url: String?,
    @SerializedName("details")
    val details: DetailsEntity?,
    @SerializedName("fileName")
    val fileName: String?,
    @SerializedName("contentType")
    val contentType: String?
)

@Entity(tableName = "details")
data class DetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val pid: Long = 0,
    @SerializedName("size")
    val size: Int?
)