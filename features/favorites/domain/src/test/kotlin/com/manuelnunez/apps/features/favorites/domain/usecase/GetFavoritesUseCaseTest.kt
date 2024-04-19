package com.manuelnunez.apps.features.favorites.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MockkAllRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
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

  @Test
  fun `call GetFavoritesUseCase invokes getAllFavorites from repository`() =
      mockkAllExtension.runTest {
        every { favoritesRepository.getAllFavorites() } returns flow { mockItems }
        useCase.prepare(Unit).test {}

        verify(exactly = 1) { favoritesRepository.getAllFavorites() }
        confirmVerified(favoritesRepository)
      }

  private val mockItems =
      List(5) { index ->
        Item(
            "$index",
            "https://example.com/$index",
            description = "description: $index",
            thumbnailUrl = "https://example.com/$index")
      }
}
