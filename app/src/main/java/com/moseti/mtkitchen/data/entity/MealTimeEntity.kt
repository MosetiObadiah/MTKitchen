package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_time")
data class MealTimeEntity (
    @PrimaryKey(autoGenerate = false)
    val mealItemId: String,

    val mealType: String,
    val dayOfTheWeek: Long,
    val timeOfDay: Long
)