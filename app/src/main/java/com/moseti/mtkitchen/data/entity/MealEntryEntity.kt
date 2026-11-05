package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_entry")
data class MealEntryEntity (
    @PrimaryKey(autoGenerate = false)
    val mealEntryId: String,

    val mealType: String,
    val mealData: Long,
    val scheduledAt: Long,
    val mealStatus: String,
    val rerollCount: Int,
    val skippedAt: Long,
    val snackNotifiedAt: Long? = null
)