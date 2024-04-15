package com.manuelnunez.apps.core.data.di

import com.manuelnunez.apps.core.data.datasource.PexeelsCatsPagingSource
import com.manuelnunez.apps.core.services.service.PexelsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

  @Provides
  fun providePexeelsCatsPagingSource(apiService: PexelsService): PexeelsCatsPagingSource {
    return PexeelsCatsPagingSource(apiService)
  }
}
