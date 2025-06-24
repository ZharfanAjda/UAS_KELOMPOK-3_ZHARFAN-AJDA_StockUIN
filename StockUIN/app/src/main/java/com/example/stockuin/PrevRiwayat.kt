package com.example.stockuin

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PrefRiwayat {
    private const val PREF_NAME = "riwayat_pref"
    private const val KEY = "list_riwayat"

    fun load(context: Context): MutableList<Riwayat> {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = pref.getString(KEY, null)
        val type = object : TypeToken<MutableList<Riwayat>>() {}.type
        return if (json != null) Gson().fromJson(json, type) else mutableListOf()
    }

    fun simpan(context: Context, data: MutableList<Riwayat>) {
        val json = Gson().toJson(data)
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY, json).apply()
    }
}
