package com.revolhope.data.common.storage.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.revolhope.data.common.storage.database.dao.ProjectDao
import com.revolhope.data.feature.project.entity.ProjectEntity
import com.revolhope.data.feature.project.entity.ProjectTargetEntity

@Database(entities = [ProjectEntity::class, ProjectTargetEntity::class], version = 1)
abstract class TrackMyProjectsDatabase : RoomDatabase() {
    abstract fun provideProjectDao(): ProjectDao
}