package com.manuelnunez.apps.core.services.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.manuelnunez.apps.core.services.executors.RetrofitServicesExecutor
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
  private const val BASE_URL = "https://api.pexels.com/v1/"
  private const val BASE_URL2 = "https://cataas.com/"

  @Provides
  @Singleton
  fun provideOkHttpClient(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
          .addInterceptor(connectivityInterceptor)
          .authenticator(PexelsKeyAuthenticator())
          .connectTimeout(60, TimeUnit.SECONDS)
          .readTimeout(60, TimeUnit.SECONDS)
          .build()

  @Singleton
  @Provides
  fun provideGson(): Gson {
    return GsonBuilder().create()
  }

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
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

  @Provides @Singleton fun provideServicesExecutor(): ServicesExecutor = RetrofitServicesExecutor()
}
