package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.data.datasource.local.FavoritesDataSource
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import io.mockk.clearAllMocks
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DetailRepositoryTest {
  private lateinit var repository: DetailRepository

  private val remoteDataSource = mockk<FavoritesDataSource>()

  @BeforeEach
  fun setUp() {
    repository = DetailRepositoryImpl(remoteDataSource)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `call to saveFavoriteItem invokes addItemToFavorites from datasource`() = runTest {
    coJustRun { remoteDataSource.addItemToFavorites(mockItems[0]) }

    repository.saveFavoriteItem(mockItems[0])

    coVerify(exactly = 1) { remoteDataSource.addItemToFavorites(mockItems[0]) }
    confirmVerified(remoteDataSource)
  }

  @Test
  fun `call to removeFavoriteItem invokes removeItemFromFavorites from datasource`() = runTest {
    coJustRun { remoteDataSource.removeItemFromFavorites(mockItems[0].photoId) }

    repository.removeFavoriteItem(mockItems[0])

    coVerify(exactly = 1) { remoteDataSource.removeItemFromFavorites(mockItems[0].photoId) }
    confirmVerified(remoteDataSource)
  }

  @Test
  fun `call to isItemFavorite invokes isItemFavorite from datasource`() {
    every { remoteDataSource.isItemFavorite(mockItems[0].photoId) } returns flow { emit(true) }

    repository.isItemFavorite(mockItems[0].photoId)

    verify(exactly = 1) { remoteDataSource.isItemFavorite(mockItems[0].photoId) }
    confirmVerified(remoteDataSource)
  }
}
