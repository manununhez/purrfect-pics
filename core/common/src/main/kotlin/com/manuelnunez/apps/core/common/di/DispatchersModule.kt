package com.manuelnunez.apps.core.common.di

import com.manuelnunez.apps.core.common.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

  @Provides
  @Singleton
  fun provideDispatcherProvider(): DispatcherProvider = object : DispatcherProvider {}
}
