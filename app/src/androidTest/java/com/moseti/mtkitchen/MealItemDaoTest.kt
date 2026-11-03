package com.moseti.mtkitchen

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moseti.mtkitchen.data.MTKDatabase
import com.moseti.mtkitchen.data.dao.MealItemDao
import com.moseti.mtkitchen.data.entity.MealItemEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class MealItemDaoTest {
    private lateinit var mealItemDao: MealItemDao
    private lateinit var db: MTKDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder<MTKDatabase>(context)
            .setDriver(BundledSQLiteDriver())
            .build()
        mealItemDao = db.mealItemDao()
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
        mealItemDao.insertMealItem(riceEntity)
        val riceMealEntity = mealItemDao.findItemByName("Rice")
        assertEquals(riceMealEntity, riceEntity)

        mealItemDao.softDeleteMealItem(riceItemId)
        val isRiceItemDeleted = mealItemDao.findIfItemIsSoftDeleted(riceItemId)
        assertEquals(true, isRiceItemDeleted)

        val isUgaliItemDeleted = mealItemDao.findIfItemIsSoftDeleted(ugaliItemId)
        assertEquals(false, isUgaliItemDeleted)
    }

}