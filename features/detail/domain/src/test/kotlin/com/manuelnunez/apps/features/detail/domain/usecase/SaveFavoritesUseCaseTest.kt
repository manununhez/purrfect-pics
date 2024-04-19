package com.manuelnunez.apps.features.detail.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MockkAllRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class SaveFavoritesUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val detailRepository = mockk<DetailRepository>()
  private lateinit var useCase: SaveFavoritesUseCase

  @BeforeEach
  fun setUp() {
    useCase =
        SaveFavoritesUseCase(detailRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @Test
  fun `call SaveFavoritesUseCase invokes saveFavoriteItem from repository`() =
      mockkAllExtension.runTest {
        useCase.prepare(mockItem).test {}

        coVerify(exactly = 1) { detailRepository.saveFavoriteItem(mockItem) }
        confirmVerified(detailRepository)
      }
}
