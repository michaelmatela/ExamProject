package com.example.examproject.features.dashboard.service

import android.content.Context
import com.example.examproject.data.JSONService
import com.example.examproject.features.dashboard.model.DashboardModel
import com.google.gson.Gson

class DashboardService(context: Context): JSONService(context) {

    fun fetchDashboardModelFromJsonFile(): DashboardModel {
        val jsonString = this.readJsonFromFile()
        return Gson().fromJson(jsonString, DashboardModel::class.java)
    }


}