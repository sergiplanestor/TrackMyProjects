package com.revolhope.data.common.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.revolhope.data.feature.project.entity.ProjectEntity
import com.revolhope.data.feature.project.entity.ProjectTargetEntity
import com.revolhope.data.feature.project.entity.ProjectWithTargetsEntity

@Dao
interface ProjectDao {

    @Transaction
    @Query("SELECT * FROM project")
    suspend fun fetchProjects(): List<ProjectWithTargetsEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(entity: ProjectEntity): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTarget(entity: ProjectTargetEntity): Long?
}