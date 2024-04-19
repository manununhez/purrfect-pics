package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.data.datasource.local.FavoritesDataSource
import com.manuelnunez.apps.core.data.utils.mockItems
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FavoritesRepositoryTest {
  private lateinit var repository: FavoritesRepository

  private val remoteDataSource = mockk<FavoritesDataSource>()

  @BeforeEach
  fun setUp() {
    repository = FavoritesRepositoryImpl(remoteDataSource)
  }

  @Test
  fun `call to getAllFavorites returns item data from datasource`() {
    every { remoteDataSource.favorites } returns flow { emit(mockItems) }

    repository.getAllFavorites()

    verify(exactly = 1) { remoteDataSource.favorites }
    confirmVerified(remoteDataSource)
  }
}
