package com.manuelnunez.apps.features.favorites.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class GetFavoritesUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val favoritesRepository = mockk<FavoritesRepository>()
  private lateinit var useCase: GetFavoritesUseCase

  @BeforeEach
  fun setUp() {
    useCase =
        GetFavoritesUseCase(favoritesRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `call GetFavoritesUseCase invokes getAllFavorites from repository`() =
      mockkAllExtension.runTest {
        every { favoritesRepository.getAllFavorites() } returns flow { mockItems }
        useCase.prepare(Unit).test {}

        verify(exactly = 1) { favoritesRepository.getAllFavorites() }
        confirmVerified(favoritesRepository)
      }
}
