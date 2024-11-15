package com.satriopndt.expertsystem.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.satriopndt.expertsystem.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("session")

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val USER_ID_KEY = stringPreferencesKey("key_id")
        private val NAME_KEY = stringPreferencesKey("key_name")
        private val TOKEN_KEY = stringPreferencesKey("token_key")
        private val ISLOGIN_KEY = stringPreferencesKey("key_isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance

            }
        }
    }

//    fun getSession(): Flow<UserModel> {
//        return dataStore.data.map { preference ->
//            UserModel(
//                preference[USER_ID_KEY] ?: "",
//                preference[NAME_KEY] ?: "",
//                preference[TOKEN_KEY] ?: "",
//                preference[ISLOGIN_KEY] ?: false
//            )
//        }
//    }


}