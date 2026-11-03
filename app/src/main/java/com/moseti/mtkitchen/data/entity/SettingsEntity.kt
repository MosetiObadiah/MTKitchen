package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity (
    @PrimaryKey(autoGenerate = false)
    val userId: String,

    val snackReminderOn: Boolean,
    val algorithmWeight: String,
    val autoTuningStepSize: Double
)