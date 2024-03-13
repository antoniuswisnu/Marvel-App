package com.example.marvelapp.di

import androidx.room.Room
import com.example.marvelapp.data.MarvelRepository
import com.example.marvelapp.data.source.local.LocalDataSource
import com.example.marvelapp.data.source.local.room.MarvelDatabase
import com.example.marvelapp.data.source.remote.RemoteDataSource
import com.example.marvelapp.data.source.remote.network.ApiService
import com.example.marvelapp.domain.repository.IMarvelRepository
import com.example.marvelapp.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MarvelDatabase>().tourismDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MarvelDatabase::class.java, "Marvel.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMarvelRepository> {
        MarvelRepository(
            get(),
            get(),
            get()
        )
    }
}