package com.example.myassignmentgithub.datasource

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class PreferenceProvider(val context: Context) {

    private val PREFS_NAME = "sharedpref12345"
    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val KEY_USER_IDS = "USER_IDS"
    private val editor: SharedPreferences.Editor = sharedPref.edit()
    private val gson = Gson()

    fun saveUserId(ids: ArrayList<Long>) {
        val jsonText = gson.toJson(ids)
        editor.putString(KEY_USER_IDS, jsonText)
        editor.apply()
    }

    fun getSavedIds(): ArrayList<Long> {
        val json: String? = sharedPref.getString(KEY_USER_IDS, null)
        val type: Type = object : TypeToken<ArrayList<Long>>() {}.type
        return if (json.isNullOrBlank())
            arrayListOf()
        else
            gson.fromJson(json, type)
    }
}