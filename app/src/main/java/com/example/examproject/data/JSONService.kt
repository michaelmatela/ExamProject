package com.example.examproject.data

import android.content.Context

open class JSONService(private val context: Context) {
    fun readJsonFromFile(): String {
        return context.assets.open("response_1709543815894.json").bufferedReader().use {
            it.readText()
        }
    }
}