package com.revolhope.domain.feature.project.repository

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.project.model.ProjectModel

interface ProjectRepository {

    suspend fun fetchProjects(): State<List<ProjectModel>>

    suspend fun insertProject(project: ProjectModel): State<Boolean>

    suspend fun updateProject(project: ProjectModel): State<Boolean>

    suspend fun deleteProject(project: ProjectModel): State<Boolean>
}