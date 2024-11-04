package com.example.ruralcosts.data.local

import com.example.ruralcosts.data.Settings
import kotlinx.coroutines.flow.Flow

interface ILocalSettingsDataStore {

    suspend fun writeSettingOne(isChecked: Boolean)

    suspend fun writeSettingTwo(isChecked: Boolean)

    suspend fun readDataStore(): Settings

    suspend fun observeDataStore(): Flow<Settings>

}