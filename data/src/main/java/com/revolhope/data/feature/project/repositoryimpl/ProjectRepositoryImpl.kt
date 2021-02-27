package com.revolhope.data.feature.project.repositoryimpl

import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.data.common.storage.database.dao.ProjectDao
import com.revolhope.data.feature.project.mapper.ProjectMapper
import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.project.model.ProjectModel
import com.revolhope.domain.feature.project.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val dao: ProjectDao
): BaseRepositoryImpl(), ProjectRepository {

    override suspend fun fetchProjects(): State<List<ProjectModel>> =
        statefulCall {
            dao.fetchProjects()?.map(ProjectMapper::fromProjectWithTargetsEntityToModel) ?: emptyList()
        }

    override suspend fun insertProject(project: ProjectModel): State<Boolean> =
        statefulCall {
            val entity = project.let(ProjectMapper::fromModelToProjectWithTargetsEntity)
            if (dao.insertProject(entity = entity.project) != null) {
                entity.targets.forEach {
                    if (dao.insertTarget(it) == null) return@statefulCall false
                }
                true
            } else {
                false
            }
        }

    override suspend fun updateProject(project: ProjectModel): State<Boolean> {
        TODO()
    }

    override suspend fun deleteProject(project: ProjectModel): State<Boolean> {
        TODO()
    }
}