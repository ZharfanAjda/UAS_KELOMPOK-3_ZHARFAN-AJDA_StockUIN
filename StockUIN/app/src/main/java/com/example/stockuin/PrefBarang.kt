package com.example.stockuin

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PrefBarang{
    private const val PREF_NAME = "barang_pref"
    private const val KEY_BARANG = "data_barang"

    fun load(context: Context): MutableList<Barang> {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = pref.getString(KEY_BARANG, null)
        val type = object : TypeToken<MutableList<Barang>>() {}.type
        return if (json != null) Gson().fromJson(json, type) else mutableListOf()
    }

    fun simpan(context: Context, data: MutableList<Barang>) {
        val json = Gson().toJson(data)
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY_BARANG, json).apply()
    }
}
