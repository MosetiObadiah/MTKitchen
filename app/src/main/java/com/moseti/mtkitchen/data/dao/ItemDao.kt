package com.moseti.mtkitchen.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.moseti.mtkitchen.data.entity.MealItemEntity
import com.moseti.mtkitchen.data.entity.MealItemMealTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert
    suspend fun addMealItem(mealItem: MealItemEntity)
    @Insert
    suspend fun addMealItemMealType(mealItemMealTyp: List<MealItemMealTypeEntity>)
    @Transaction
    suspend fun addMealItemANDMealItemMealType(mealItem: MealItemEntity, mealItemMealType: List<MealItemMealTypeEntity>) {
        addMealItem(mealItem)
        addMealItemMealType(mealItemMealType)
    }

    @Update
    suspend fun editMealItem(mealItem: MealItemEntity)
    @Update
    suspend fun editMealItemMealType(mealItemMealType: List<MealItemMealTypeEntity>)
    @Transaction
    suspend fun editMealItemANDMealItemMealType(mealItem: MealItemEntity, mealItemMealType: List<MealItemMealTypeEntity>){
        editMealItem(mealItem)
        editMealItemMealType(mealItemMealType)
    }

    //TODO: check if setting isDeleted to 0/1 causes a bug, or it should be 1
    @Query("UPDATE meal_item SET isDeleted = 1 WHERE itemId = :mealItemId")
    suspend fun softDeleteMealItem(mealItemId: String)
    @Query("DELETE FROM meal_item_rule WHERE itemA = :mealItemId OR itemB = :mealItemId")
    suspend fun deleteItemRule(mealItemId: String)
    @Transaction
    suspend fun softDeleteMealItemANDItsRules(mealItemId: String) {
        softDeleteMealItem(mealItemId)
        deleteItemRule(mealItemId)
    }

    @Query("SELECT * FROM meal_item WHERE itemId = :mealItemId")
    suspend fun getMealItemById(mealItemId: String) : MealItemEntity?

    @Query("SELECT * FROM meal_item WHERE itemName = :mealItemName")
    suspend fun findActiveItemByName(mealItemName: String) : MealItemEntity?

    @Query("SELECT * FROM meal_item WHERE isDeleted = 0 ORDER BY itemName")
    fun observeActiveItems(): Flow<MealItemEntity>

}