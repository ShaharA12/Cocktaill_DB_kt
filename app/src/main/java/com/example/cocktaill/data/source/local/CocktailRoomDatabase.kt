package com.example.cocktaill.data.source.local

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.cocktaill.data.source.entity.Drink
import androidx.room.Room


/**
 * Created by Shahar.A. on 21/02/2019.
 */

@Database(entities = [Drink::class], version = 1)
abstract class CocktailRoomDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

    companion object {

        private var INSTANCE: CocktailRoomDatabase? = null

        private val LOCK = Any()
        //        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
//            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        fun getInstance(context: Context): CocktailRoomDatabase {
            synchronized(LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CocktailRoomDatabase::class.java, "cocktail.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }


    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            CocktailRoomDatabase::class.java, "cocktail.db"
        )
            .build()

}
}