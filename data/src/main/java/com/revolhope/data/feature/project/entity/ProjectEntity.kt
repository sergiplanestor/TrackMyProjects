package com.revolhope.data.feature.project.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "project")
data class ProjectEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "start_date") val startDate: Long,
    @ColumnInfo(name = "end_date") val endDate: Long?,
    @ColumnInfo(name = "default_desired_value") val defaultDesiredValue: String,
    @ColumnInfo(name = "is_notification_enabled") val isNotificationEnabled: Boolean
)

@Entity(tableName = "target")
data class ProjectTargetEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "project_id") val projectId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "desired_value") val desiredValue: String,
    @ColumnInfo(name = "current_value") val currentValue: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "is_notification_enabled") val isNotificationEnabled: Boolean,
    @ColumnInfo(name = "next_notification") val nextNotification: Long?,
)

data class ProjectWithTargetsEntity(
    @Embedded val project: ProjectEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    //@TypeConverters(ListConverter::class)
    val targets: List<ProjectTargetEntity>
)

object ListConverter {

    private const val SEPARATOR = ";;"

    @TypeConverter
    fun toModel(value: String): List<ProjectTargetEntity> =
        value.split(SEPARATOR).map {
            Gson().fromJson(it, ProjectTargetEntity::class.java)
        }

    fun toEntity(model: List<ProjectTargetEntity>): String {
        var fakeEntity = ""
        model.forEachIndexed { index, data ->
            fakeEntity += Gson().toJson(data) + if (index != model.lastIndex) SEPARATOR else ""
        }
        return fakeEntity
    }
}