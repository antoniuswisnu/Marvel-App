package com.example.marvelapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marvelapp.data.source.local.entity.MarvelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Query("SELECT * FROM marvel")
    fun getAllMarvel(): Flow<List<MarvelEntity>>

    @Query("SELECT * FROM marvel where isFavorite = 1")
    fun getFavoriteMarvel(): Flow<List<MarvelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarvel(marvel: List<MarvelEntity>)

    @Update
    fun updateFavoriteMarvel(marvel: MarvelEntity)
}