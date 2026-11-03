package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "shown_suggestions",
    primaryKeys = ["mealEntryId", "suggestionKey"],
    foreignKeys = [
        ForeignKey(
            entity = MealEntryEntity::class,
            parentColumns = ["mealEntryId"],
            childColumns = ["mealEntryId"],
            onDelete = CASCADE
        )
    ]
)
data class ShownSuggestionEntity (
    val mealEntryId: String,
    val suggestionKey: String,
    val shownAt: Long
)