package com.example.weatherman.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PrefUtil(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Preferences")

    suspend fun addLocation(name:String,value: String){
        context.dataStore.edit { data ->
            data[stringPreferencesKey(name)] = value
        }
    }

    suspend fun getLocation(key:String):String?{
        return context.dataStore.data.map { data->data[stringPreferencesKey(key)] }.first()
    }
}
