package com.example.marvelapp.data.source.local

import com.example.marvelapp.data.source.local.entity.MarvelEntity
import com.example.marvelapp.data.source.local.room.MarvelDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val marvelDao: MarvelDao) {

    fun getAllMarvel(): Flow<List<MarvelEntity>> = marvelDao.getAllMarvel()

    fun getFavoriteMarvel(): Flow<List<MarvelEntity>> = marvelDao.getFavoriteMarvel()

    suspend fun insertMarvel(marvelList: List<MarvelEntity>) = marvelDao.insertMarvel(marvelList)

    fun setFavoriteMarvel(marvel: MarvelEntity, newState: Boolean) {
        marvel.isFavorite = newState
        marvelDao.updateFavoriteMarvel(marvel)
    }
}