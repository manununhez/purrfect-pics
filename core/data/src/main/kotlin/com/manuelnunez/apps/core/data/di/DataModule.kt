package com.manuelnunez.apps.core.data.di

import com.manuelnunez.apps.core.data.datasource.remote.CataasCatsRemoteDataSource
import com.manuelnunez.apps.core.data.datasource.remote.CataasCatsRemoteDataSourceImpl
import com.manuelnunez.apps.core.data.datasource.remote.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.datasource.remote.PexelsCatsRemoteDataSourceImpl
import com.manuelnunez.apps.core.data.repository.DetailRepositoryImpl
import com.manuelnunez.apps.core.data.repository.FavoritesRepositoryImpl
import com.manuelnunez.apps.core.data.repository.HomeRepositoryImpl
import com.manuelnunez.apps.core.data.repository.SeeMoreRepositoryImpl
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
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
  abstract fun bindsDetailRepository(detailRepository: DetailRepositoryImpl): DetailRepository

  @Singleton
  @Binds
  abstract fun bindsFavoritesRepository(
      seeMoreRepository: FavoritesRepositoryImpl
  ): FavoritesRepository

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
