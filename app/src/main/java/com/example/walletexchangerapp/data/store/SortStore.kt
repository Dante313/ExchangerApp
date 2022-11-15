package com.example.walletexchangerapp.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.walletexchangerapp.domain.model.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SortStore(private val context: Context) {

    var sort = Sort.NAME_ASC

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SORT_STORE_NAME)

    val sortFlow: Flow<Sort> = context.dataStore.data.map { preferences ->
        Sort.valueOf(preferences[KEY_SORT] ?: Sort.NAME_ASC.name).also {
            sort = it
        }
    }

    suspend fun setSort(sort: Sort) {
        context.dataStore.edit { preferences ->
            preferences[KEY_SORT] = sort.name
        }
    }

    companion object {
        private const val SORT_STORE_NAME = "sort_store"
        private val KEY_SORT = stringPreferencesKey("key_sort")
    }
}