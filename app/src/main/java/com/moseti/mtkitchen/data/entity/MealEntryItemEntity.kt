package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.RESTRICT
import androidx.room.Index

@Entity(
    tableName = "meal_entry_item",
    primaryKeys = ["mealEntryId", "itemId", "role"],
    foreignKeys = [
        ForeignKey(
            entity = MealEntryEntity::class,
            parentColumns = ["mealEntryId"],
            childColumns = ["mealEntryId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = MealItemEntity::class,
            parentColumns = ["itemId"],
            childColumns = ["itemId"],
            onDelete = RESTRICT
        )
    ],
    indices = [Index("itemId")]
)
class MealEntryItemEntity (
    val mealEntryId: String,
    val itemId: String,
    val role: String
)