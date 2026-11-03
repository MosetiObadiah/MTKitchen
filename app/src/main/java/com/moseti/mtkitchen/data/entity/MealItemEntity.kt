package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_item")
data class MealItemEntity (
    @PrimaryKey(autoGenerate = false)
    val itemId: String,

    val itemName: String,
    val itemPrice: Double,
    val itemRating: Int,
    val itemTuning: Double,
    val isDeleted: Boolean,
    val isPrimaryFood: Boolean,
    val isSecondaryFood: Boolean,
    val isSnack: Boolean
)