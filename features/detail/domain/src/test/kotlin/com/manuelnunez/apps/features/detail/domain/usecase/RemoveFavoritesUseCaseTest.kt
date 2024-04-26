package com.manuelnunez.apps.features.detail.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RemoveFavoritesUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val detailRepository = mockk<DetailRepository>()
  private lateinit var useCase: RemoveFavoritesUseCase

  @BeforeEach
  fun setUp() {
    useCase =
        RemoveFavoritesUseCase(detailRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `call RemoveFavoritesUseCase invokes removeFavoriteItem from repository`() =
      mockkAllExtension.runTest {
        useCase.prepare(mockItems[0]).test {}

        coVerify(exactly = 1) { detailRepository.removeFavoriteItem(mockItems[0]) }
        confirmVerified(detailRepository)
      }
}
