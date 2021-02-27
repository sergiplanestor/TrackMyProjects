package com.revolhope.domain.feature.project.model

import com.revolhope.domain.common.model.DateModel

data class ProjectModel(
    val id: String,
    val name: String,
    val startDate: DateModel,
    val endDate: DateModel?,
    val targets: List<ProjectTargetModel>,
    val defaultDesiredValue: String,
    val isNotificationEnabled: Boolean
)