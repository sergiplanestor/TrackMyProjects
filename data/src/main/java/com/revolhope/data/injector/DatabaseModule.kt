package com.revolhope.data.injector

import android.content.Context
import androidx.room.Room
import com.revolhope.data.common.storage.database.dao.ProjectDao
import com.revolhope.data.common.storage.database.db.TrackMyProjectsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "track-my-projects-database"

    @Singleton
    @Provides
    fun providesDatabase(
        context: Context
    ): TrackMyProjectsDatabase =
        Room.databaseBuilder(context, TrackMyProjectsDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun providesProjectDao(
        database: TrackMyProjectsDatabase
    ) : ProjectDao = database.provideProjectDao()
}