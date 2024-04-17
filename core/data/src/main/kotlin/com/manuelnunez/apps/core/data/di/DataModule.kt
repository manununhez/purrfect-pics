package com.manuelnunez.apps.core.data.di

import com.manuelnunez.apps.core.data.datasource.CataasCatsRemoteDataSource
import com.manuelnunez.apps.core.data.datasource.CataasCatsRemoteDataSourceImpl
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSourceImpl
import com.manuelnunez.apps.core.data.repository.HomeRepositoryImpl
import com.manuelnunez.apps.core.data.repository.SeeMoreRepositoryImpl
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
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
  abstract fun bindsSeeMoreRepository(seeMoreRepository: SeeMoreRepositoryImpl): SeeMoreRepository

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
