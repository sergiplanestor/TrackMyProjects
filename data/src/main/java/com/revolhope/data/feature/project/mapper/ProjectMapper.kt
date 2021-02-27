package com.revolhope.data.feature.project.mapper

import com.revolhope.data.feature.project.entity.ProjectEntity
import com.revolhope.data.feature.project.entity.ProjectTargetEntity
import com.revolhope.data.feature.project.entity.ProjectWithTargetsEntity
import com.revolhope.domain.common.model.DateModel
import com.revolhope.domain.feature.project.model.ProjectModel
import com.revolhope.domain.feature.project.model.ProjectTargetModel
import java.util.*

object ProjectMapper {

    private const val SIMPLE_DATE_FORMAT = "dd/MM/yyyy"

    fun fromProjectWithTargetsEntityToModel(entity: ProjectWithTargetsEntity): ProjectModel =
        ProjectModel(
            id = entity.project.id,
            name = entity.project.name,
            startDate = parseToDateModel(entity.project.startDate),
            endDate = entity.project.endDate?.let(::parseToDateModel),
            targets = entity.targets.map(::fromTargetEntityToModel),
            defaultDesiredValue = entity.project.defaultDesiredValue,
            isNotificationEnabled = entity.project.isNotificationEnabled
        )

    private fun fromTargetEntityToModel(entity: ProjectTargetEntity): ProjectTargetModel =
        ProjectTargetModel(
            id = entity.id,
            projectId = entity.projectId,
            name = entity.name,
            desiredValue = entity.desiredValue,
            currentValue = entity.currentValue,
            date = parseToDateModel(entity.date),
            isNotificationEnabled = entity.isNotificationEnabled,
            nextNotification = entity.nextNotification?.let(::parseToDateModel)
        )

    fun fromModelToProjectWithTargetsEntity(model: ProjectModel): ProjectWithTargetsEntity =
        ProjectWithTargetsEntity(
            project = fromModelToProjectEntity(model),
            targets = model.targets.map(::fromModelToTargetEntity)
        )

    private fun fromModelToProjectEntity(model: ProjectModel): ProjectEntity =
        ProjectEntity(
            id = model.id,
            name = model.name,
            startDate = model.startDate.date.time,
            endDate = model.endDate?.date?.time,
            defaultDesiredValue = model.defaultDesiredValue,
            isNotificationEnabled = model.isNotificationEnabled
        )

    private fun fromModelToTargetEntity(model: ProjectTargetModel): ProjectTargetEntity =
        ProjectTargetEntity(
            id = model.id,
            projectId = model.projectId,
            name = model.name,
            desiredValue = model.desiredValue,
            currentValue = model.currentValue,
            date = model.date.date.time,
            isNotificationEnabled = model.isNotificationEnabled,
            nextNotification = model.nextNotification?.date?.time
        )

    private fun parseToDateModel(value: Long): DateModel =
        DateModel(
            date = Date(value),
            formatted = SIMPLE_DATE_FORMAT.format(Locale.ROOT, value)
        )
}