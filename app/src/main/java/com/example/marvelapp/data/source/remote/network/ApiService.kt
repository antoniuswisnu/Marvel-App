package com.example.marvelapp.data.source.remote.network

import com.example.marvelapp.data.source.remote.response.Data
import com.example.marvelapp.data.source.remote.response.MarvelResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): Data
}