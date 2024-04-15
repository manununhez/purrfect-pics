package com.manuelnunez.apps.core.services.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.manuelnunez.apps.core.services.executors.ServiceExecutorRetrofitImpl
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.interceptor.ConnectivityInterceptor
import com.manuelnunez.apps.core.services.interceptor.PexelsKeyAuthenticator
import com.manuelnunez.apps.core.services.service.CataasService
import com.manuelnunez.apps.core.services.service.PexelsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
  /**
   * API interchangeable. In case of using PEXELS API, use PEXELS_BASE_URL with the PexelKeyAuthenticator interceptor.
   * In case of using CATAAS API, use CATAAS_BASE_URL, an remove PexelsKeyAuth interceptor. Finally, select
   * RemoteDataSources accordingly.
   */
  private const val PEXELS_BASE_URL = "https://api.pexels.com/v1/"
  private const val CATAAS_BASE_URL = "https://cataas.com/"

  @Provides
  @Singleton
  fun provideOkHttpClient(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
          .addInterceptor(connectivityInterceptor)
          .authenticator(PexelsKeyAuthenticator())
          .connectTimeout(30, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS)
          .build()

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(PEXELS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
  }

  @Singleton
  @Provides
  fun providePexelsService(retrofit: Retrofit): PexelsService =
      retrofit.create(PexelsService::class.java)

  @Singleton
  @Provides
  fun provideCataasService(retrofit: Retrofit): CataasService =
      retrofit.create(CataasService::class.java)

  @Provides
  @Singleton
  fun provideConnectivityInterceptor(
      @ApplicationContext context: Context
  ): ConnectivityInterceptor {
    return ConnectivityInterceptor(context)
  }

  @Provides
  @Singleton
  fun provideServicesExecutor(): ServicesExecutor = ServiceExecutorRetrofitImpl()

  @Singleton
  @Provides
  fun provideGson(): Gson {
    return GsonBuilder().create()
  }
}
