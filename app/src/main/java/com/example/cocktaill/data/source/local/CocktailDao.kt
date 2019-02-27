package com.example.cocktaill.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktaill.data.source.entity.Drink

/**
 * Created by Shahar.A. on 21/02/2019.
 */
@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: List<Drink>)

    @Query("DELETE FROM cocktails_table")
    fun deleteAll()

    @Query("SELECT * from cocktails_table ORDER BY strDrink ASC")
    fun LiveDatagetAllCocktails(): LiveData<List<Drink>>

//    @Query("SELECT * from cocktails_table where idDrink = $id")
//    fun getCocktailByID(id: String)
}