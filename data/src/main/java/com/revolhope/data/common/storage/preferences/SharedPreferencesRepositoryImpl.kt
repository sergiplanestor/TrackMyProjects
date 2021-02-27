package com.revolhope.data.common.storage.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.user.model.UserModel
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseRepositoryImpl(), SharedPreferencesRepository {

    companion object {
        private const val USER = "shared.preferences.user"
    }

    override suspend fun fetchUser(): State<UserModel?> =
        statefulCall {
            sharedPreferences.getString(USER, null)?.let {
                Gson().fromJson(it, UserModel::class.java)
            }
        }

    override suspend fun insertUser(user: UserModel): State<Boolean> =
        statefulCall {
            sharedPreferences.edit().apply {
                putString(USER, Gson().toJson(user))
                apply()
            }
            true
        }


    override suspend fun updateUser(user: UserModel): State<Boolean> =
        statefulCall {
            sharedPreferences.edit().apply {
                putString(USER, Gson().toJson(user))
                apply()
            }
            true
        }

    override suspend fun deleteUser(): State<Boolean> =
        statefulCall {
            sharedPreferences.edit().remove(USER)
            true
        }
}