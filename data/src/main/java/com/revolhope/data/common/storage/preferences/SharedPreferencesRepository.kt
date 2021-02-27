package com.revolhope.data.common.storage.preferences

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.user.model.UserModel

interface SharedPreferencesRepository {

    suspend fun fetchUser(): State<UserModel?>

    suspend fun insertUser(user: UserModel): State<Boolean>

    suspend fun updateUser(user: UserModel): State<Boolean>

    suspend fun deleteUser(): State<Boolean>

}