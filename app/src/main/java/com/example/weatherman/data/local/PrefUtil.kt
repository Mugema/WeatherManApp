package com.example.weatherman.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherman.domain.models.DataStoreLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefUtil(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Preferences")

    suspend fun addLocation(name:String,value: String,lat: Double, lon: Double){
        context.dataStore.edit { data ->
            data[stringPreferencesKey(name)] = value
            data[stringPreferencesKey("Lat")]=lat.toString()
            data[stringPreferencesKey("Lon")]=lon.toString()
        }
    }

    fun getLocation(key:String): Flow<DataStoreLocation>{
        return context.dataStore.data.map { data->
            DataStoreLocation(
                city = data[stringPreferencesKey(key)]?:"",
                lat = data[stringPreferencesKey("Lat")]?.toDouble() ?: 0.0,
                lon = data[stringPreferencesKey("Lon")]?.toDouble() ?: 0.0,
            )
        }
    }
}
