package com.manuelnunez.apps.core.data.di

import com.manuelnunez.apps.core.data.HomeRepositoryImpl
import com.manuelnunez.apps.core.data.datasource.CataasCatsRemoteDataSource
import com.manuelnunez.apps.core.data.datasource.CataasCatsRemoteDataSourceImpl
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSourceImpl
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

  @Singleton
  @Binds
  abstract fun bindsHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository

  @Singleton
  @Binds
  abstract fun providePexelsCatsRemoteDataSource(
      remoteDataSourceImpl: PexelsCatsRemoteDataSourceImpl
  ): PexelsCatsRemoteDataSource

  @Singleton
  @Binds
  abstract fun provideCataasCatsRemoteDataSource(
      remoteDataSourceImpl: CataasCatsRemoteDataSourceImpl
  ): CataasCatsRemoteDataSource
}
