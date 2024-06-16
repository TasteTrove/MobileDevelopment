package com.example.tastetrove.di

import com.example.tastetrove.data.repo.history.HistoryRepository
import com.example.tastetrove.data.repo.history.HistoryRepositoryImpl
import com.example.tastetrove.data.repo.news.NewsRepository
import com.example.tastetrove.data.repo.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideHistoryRepository(repo: HistoryRepositoryImpl): HistoryRepository


    @Binds
    abstract fun provideNewsRepository(repo: NewsRepositoryImpl): NewsRepository

}