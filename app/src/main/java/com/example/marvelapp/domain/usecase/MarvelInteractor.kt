package com.example.marvelapp.domain.usecase

import com.example.marvelapp.domain.model.Marvel
import com.example.marvelapp.domain.repository.IMarvelRepository

class MarvelInteractor(private val marvelRepository: IMarvelRepository): MarvelUseCase {

    override fun getAllMarvel() = marvelRepository.getAllMarvel()

    override fun getFavoriteMarvel() = marvelRepository.getFavoriteMarvel()

    override fun setFavoriteMarvel(marvel: Marvel, state: Boolean) = marvelRepository.setFavoriteMarvel(marvel, state)
}