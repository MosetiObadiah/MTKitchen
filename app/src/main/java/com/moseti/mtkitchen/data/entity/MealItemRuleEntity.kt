package com.moseti.mtkitchen.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    tableName = "meal_item_rule",
    primaryKeys = ["ruleId"],
    foreignKeys = [
        ForeignKey(
            entity = MealItemEntity::class,
            parentColumns = ["itemId"],
            childColumns = ["itemA"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = MealItemEntity::class,
            parentColumns = ["itemId"],
            childColumns = ["itemB"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(value = ["itemA", "itemB"], unique = true), Index(value = ["itemB"])]
)

data class MealItemRuleEntity (
    val ruleId: String,
    val itemA: String,
    val itemB: String
)