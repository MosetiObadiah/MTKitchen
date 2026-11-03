package com.moseti.mtkitchen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moseti.mtkitchen.data.dao.MealItemDao
import com.moseti.mtkitchen.data.entity.MealEntryEntity
import com.moseti.mtkitchen.data.entity.MealEntryItemEntity
import com.moseti.mtkitchen.data.entity.MealItemEntity
import com.moseti.mtkitchen.data.entity.MealItemMealTypeEntity
import com.moseti.mtkitchen.data.entity.MealItemRuleEntity
import com.moseti.mtkitchen.data.entity.MealTimeEntity
import com.moseti.mtkitchen.data.entity.SettingsEntity
import com.moseti.mtkitchen.data.entity.ShownSuggestionEntity
import kotlin.concurrent.Volatile

@Database(
    entities = [
        MealEntryEntity::class,
        MealEntryItemEntity::class,
        MealItemEntity::class,
        MealItemMealTypeEntity::class,
        MealItemRuleEntity::class,
        MealTimeEntity::class,
        SettingsEntity::class,
        ShownSuggestionEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class MTKDatabase: RoomDatabase() {
    abstract fun mealItemDao(): MealItemDao

    companion object {
        private const val DB_NAME = "mtkitchen_db"

        @Volatile
        private var INSTANCE: MTKDatabase? = null

        fun getInstance(context: Context): MTKDatabase {
            INSTANCE?.let { return it }

            synchronized(this) {
                INSTANCE?.let { return it }
                val instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            MTKDatabase::class.java,
                            DB_NAME,
                        )
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}