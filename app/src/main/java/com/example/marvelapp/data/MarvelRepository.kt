package com.example.marvelapp.data

import com.example.marvelapp.data.source.local.LocalDataSource
import com.example.marvelapp.data.source.remote.RemoteDataSource
import com.example.marvelapp.data.source.remote.network.ApiResponse
import com.example.marvelapp.data.source.remote.response.MarvelResponse
import com.example.marvelapp.domain.model.Marvel
import com.example.marvelapp.domain.repository.IMarvelRepository
import com.example.marvelapp.utils.AppExecutors
import com.example.marvelapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MarvelRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMarvelRepository {

    override fun getAllMarvel(): Flow<Resource<List<Marvel>>> =
        object : NetworkBoundResource<List<Marvel>, List<MarvelResponse>>() {
            override fun loadFromDB(): Flow<List<Marvel>> {
                return localDataSource.getAllMarvel().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Marvel>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<MarvelResponse>>> =
                remoteDataSource.getAllMarvel()

            override suspend fun saveCallResult(data: List<MarvelResponse>) {
                val marvelList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMarvel(marvelList)
            }
        }.asFlow()

    override fun getFavoriteMarvel(): Flow<List<Marvel>> {
        return localDataSource.getFavoriteMarvel().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMarvel(marvel: Marvel, state: Boolean) {
        val marvelEntity = DataMapper.mapDomainToEntity(marvel)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMarvel(marvelEntity, state) }
    }
}