package com.revolhope.data.feature.user

import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.data.common.storage.preferences.SharedPreferencesRepository
import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.user.model.UserModel
import com.revolhope.domain.feature.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val preferencesRepository: SharedPreferencesRepository
) : BaseRepositoryImpl(), UserRepository {

    override suspend fun registerUser(user: UserModel): State<Boolean> =
        preferencesRepository.insertUser(user)

    override suspend fun fetchUser(): State<UserModel?> =
        preferencesRepository.fetchUser()
}