package com.manuelnunez.apps.core.services.di

import com.manuelnunez.apps.core.domain.network.NetworkManager
import com.manuelnunez.apps.core.services.executors.RetrofitServicesExecutor
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.network.NetworkManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
  @Singleton
  @Binds
  abstract fun provideServiceExecutor(
      retrofitServicesExecutor: RetrofitServicesExecutor
  ): ServicesExecutor

  @Singleton
  @Binds
  abstract fun provideNetworkManager(networkManagerImpl: NetworkManagerImpl): NetworkManager
}
