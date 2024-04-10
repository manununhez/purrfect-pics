package com.manuelnunez.apps.core.data.di

import com.manuelnunez.apps.core.data.CatsRemoteDataSourceImpl
import com.manuelnunez.apps.core.data.HomeRepositoryImpl
import com.manuelnunez.apps.core.data.datasource.CatsRemoteDataSource
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
  abstract fun provideCatsRemoteDataSource(
      remoteDataSourceImpl: CatsRemoteDataSourceImpl
  ): CatsRemoteDataSource
}
