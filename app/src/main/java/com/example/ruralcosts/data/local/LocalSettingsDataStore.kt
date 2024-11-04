package com.example.ruralcosts.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.ruralcosts.data.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private object SettingsKeys {
    val SETTING_ONE_KEY = booleanPreferencesKey("SETTING_ONE_KEY")
    val SETTING_TWO_KEY = booleanPreferencesKey("SETTING_TWO_KEY")
}

@Singleton
class LocalSettingsDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ILocalSettingsDataStore {

    override suspend fun writeSettingOne(isChecked: Boolean) {
        dataStore.edit { settings -> settings[SettingsKeys.SETTING_ONE_KEY] = isChecked }
    }

    override suspend fun writeSettingTwo(isChecked: Boolean) {
        dataStore.edit { settings -> settings[SettingsKeys.SETTING_TWO_KEY] = isChecked }
    }

    override suspend fun readDataStore(): Settings {
        TODO("Not yet implemented")
    }

    override suspend fun observeDataStore(): Flow<Settings> {
        return dataStore.data.map {
                settings ->
            val settingOne = settings[SettingsKeys.SETTING_ONE_KEY] ?: false
            val settingTwo = settings[SettingsKeys.SETTING_TWO_KEY] ?: false
            Settings(settingOne, settingTwo)
        }.catch { exception ->
            if (exception !is IOException) throw exception
        }
    }

}