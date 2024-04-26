package com.manuelnunez.apps.core.data.di

import com.manuelnunez.apps.core.data.repository.fake.FakeDetailRepository
import com.manuelnunez.apps.core.data.repository.fake.FakeFavoritesRepository
import com.manuelnunez.apps.core.data.repository.fake.FakeHomeRepository
import com.manuelnunez.apps.core.data.repository.fake.FakeSeeMoreRepository
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * This Test Module is used as replace of the DataModule when testing is done. In case of the
 * MainNavigationTest, this avoid to call network and emit fake data instead.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class],
)
internal interface TestDataModule {
  @Singleton
  @Binds
  abstract fun bindsHomeRepository(homeRepository: FakeHomeRepository): HomeRepository

  @Singleton
  @Binds
  abstract fun bindsSeeMoreRepository(seeMoreRepository: FakeSeeMoreRepository): SeeMoreRepository

  @Singleton
  @Binds
  abstract fun bindsDetailRepository(detailRepository: FakeDetailRepository): DetailRepository

  @Singleton
  @Binds
  abstract fun bindsFavoritesRepository(
      seeMoreRepository: FakeFavoritesRepository
  ): FavoritesRepository
}
