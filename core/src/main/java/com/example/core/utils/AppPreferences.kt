package com.example.core.utils

import android.content.SharedPreferences
import androidx.core.content.edit

class AppPreferences(private val preferences: SharedPreferences) {

    fun setString(key: String, value:String){
        preferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String, defaultValue: String): String =
        preferences.getString(key, defaultValue) ?: defaultValue

    fun setInt(key: String, value: Int){
        preferences.edit {
            putInt(key, value)
        }
    }

    fun getInt(key: String): Int = preferences.getInt(key, 0)

    fun setBoolean(key: String, value: Boolean){
        preferences.edit{
            putBoolean(key, value)
        }
    }

    fun delete(key: String) {
        preferences.edit().remove(key).apply()
    }

}