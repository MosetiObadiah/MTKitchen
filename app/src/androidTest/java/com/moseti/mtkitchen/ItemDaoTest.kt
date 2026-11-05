package com.moseti.mtkitchen

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.moseti.mtkitchen.data.MTKDatabase
import com.moseti.mtkitchen.data.dao.ItemDao
import com.moseti.mtkitchen.data.entity.MealItemEntity
import com.moseti.mtkitchen.data.entity.MealItemMealTypeEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var db: MTKDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder<MTKDatabase>(context)
            .setDriver(BundledSQLiteDriver())
            .build()
        itemDao = db.mealItemDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test()
    fun writeUserAndReadInList() = runTest {
        val riceItemId = UUID.randomUUID().toString()
        val riceEntity = MealItemEntity(
            itemId = riceItemId,
            itemName = "Rice",
            itemPrice = 100.00,
            itemRating = 7,
            itemTuning = 5.0,
            isDeleted = false,
            isPrimaryFood = true,
            isSecondaryFood = false,
            isSnack = false
        )

        val riceForBreakfast = MealItemMealTypeEntity(
            itemId = riceItemId,
            mealType = "breakfast"
        )

        val riceForLuch = MealItemMealTypeEntity(
            itemId = riceItemId,
            mealType = "lunch"
        )

        val riceForSupper = MealItemMealTypeEntity(
            itemId = riceItemId,
            mealType = "supper"
        )

        itemDao.addMealItemANDMealItemMealType(riceEntity, listOf(riceForBreakfast, riceForLuch, riceForSupper))
        val returnedMealItem = itemDao.getMealItemById(riceItemId)
        assertEquals(riceItemId, returnedMealItem!!.itemId)


        val ugaliItemId = UUID.randomUUID().toString()
        val ugaliEntity = MealItemEntity(
            itemId = ugaliItemId,
            itemName = "Ugali",
            itemPrice = 100.00,
            itemRating = 7,
            itemTuning = 5.0,
            isDeleted = false,
            isPrimaryFood = true,
            isSecondaryFood = false,
            isSnack = false
        )

        val ugaliForLuch = MealItemMealTypeEntity(
            itemId = ugaliItemId,
            mealType = "lunch"
        )

        val ugaliForSupper = MealItemMealTypeEntity(
            itemId = ugaliItemId,
            mealType = "supper"
        )

        itemDao.addMealItemANDMealItemMealType(ugaliEntity, listOf(ugaliForLuch, ugaliForSupper))

        itemDao.observeActiveItems().test {
            val expectedList = listOf(riceEntity, ugaliEntity)
            val returnedList = awaitItem()
            assertEquals(riceEntity, returnedList)
            awaitComplete()
        }
    }

}