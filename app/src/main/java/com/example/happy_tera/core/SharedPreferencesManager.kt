package com.example.happy_tera.core

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val PREFS_NAME = "AppPreferences"
    private const val TIME_IN_MILLISECONDS = "time_in_milliseconds"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveTime(timeInMillis: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(TIME_IN_MILLISECONDS, timeInMillis)
        editor.apply()
    }

    fun getTime(): Long {
        return sharedPreferences.getLong(TIME_IN_MILLISECONDS, 0L)
    }

    fun clearTime() {
        val editor = sharedPreferences.edit()
        editor.remove(TIME_IN_MILLISECONDS)
        editor.apply()
    }

}