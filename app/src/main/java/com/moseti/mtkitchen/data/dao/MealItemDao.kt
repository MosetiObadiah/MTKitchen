package com.moseti.mtkitchen.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.moseti.mtkitchen.data.entity.MealItemEntity

@Dao
interface MealItemDao {
    @Insert
    suspend fun insertMealItem(mealItem: MealItemEntity)

    @Query("SELECT isDeleted FROM meal_item WHERE itemId = :itemID")
    suspend fun findIfItemIsSoftDeleted(itemID:String) : Boolean?

    @Query("UPDATE meal_item SET isDeleted = 1 WHERE itemId = :itemId")
    suspend fun softDeleteMealItem(itemId: String)

    @Query("SELECT * FROM meal_item WHERE itemName = :itemName")
    suspend fun findItemByName(itemName: String) : MealItemEntity?
}