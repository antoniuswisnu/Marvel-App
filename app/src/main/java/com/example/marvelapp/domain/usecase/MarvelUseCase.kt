package com.example.marvelapp.domain.usecase

import com.example.marvelapp.data.Resource
import com.example.marvelapp.domain.model.Marvel
import kotlinx.coroutines.flow.Flow

interface MarvelUseCase {
    fun getAllMarvel(): Flow<Resource<List<Marvel>>>
    fun getFavoriteMarvel(): Flow<List<Marvel>>
    fun setFavoriteMarvel(tourism: Marvel, state: Boolean)
}