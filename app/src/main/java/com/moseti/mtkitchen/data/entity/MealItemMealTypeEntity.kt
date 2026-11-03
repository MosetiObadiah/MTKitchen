package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "meal_item_meal_type",
    primaryKeys = ["itemId", "mealType"],
    foreignKeys = [
        ForeignKey(
            entity = MealItemEntity::class,
            parentColumns = ["itemId"],
            childColumns = ["itemId"],
            onDelete = CASCADE,
        )
    ]

)
data class MealItemMealTypeEntity (
    val itemId: String,
    val mealType: String
)