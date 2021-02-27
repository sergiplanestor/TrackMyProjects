package com.revolhope.domain.feature.project.model

import com.revolhope.domain.common.model.DateModel

data class ProjectTargetModel(
    val id: String,
    val projectId: String,
    val name: String,
    val desiredValue: String,
    val currentValue: String,
    val date: DateModel,
    val isNotificationEnabled: Boolean,
    val nextNotification: DateModel?
)