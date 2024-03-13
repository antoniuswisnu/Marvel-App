package com.example.marvelapp.utils

import com.example.marvelapp.data.source.local.entity.MarvelEntity
import com.example.marvelapp.data.source.remote.response.ResultsItem
import com.example.marvelapp.domain.model.Marvel

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<MarvelEntity> {
        val marvelList = ArrayList<MarvelEntity>()
        input.map {
            val marvel = MarvelEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                like = it.like,
                thumbnail = it.thumbnail,
                isFavorite = false
            )
            marvelList.add(marvel)
        }
        return marvelList
    }

    fun mapEntitiesToDomain(input: List<MarvelEntity>): List<Marvel> =
        input.map {
            Marvel(
                id = it.id,
                name = it.name,
                description = it.description,
                like = it.like,
                thumbnail = it.thumbnail,
                isFavorite = false
            )
        }

    fun mapDomainToEntity(input: Marvel) = MarvelEntity(
        id = it.id,
        name = it.name,
        description = it.description,
        like = it.like,
        thumbnail = it.thumbnail,
        isFavorite = false
    )
}