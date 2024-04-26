package com.manuelnunez.apps.features.detail.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class GetFavoriteStatusUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val detailRepository = mockk<DetailRepository>()
  private lateinit var useCase: GetFavoriteStatusUseCase

  @BeforeEach
  fun setUp() {
    useCase =
        GetFavoriteStatusUseCase(
            detailRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `call GetFavoriteStatusUseCase invokes isItemFavorite from repository`() =
      mockkAllExtension.runTest {
        every { detailRepository.isItemFavorite(mockItems[0].photoId) } returns flow { emit(false) }
        useCase.prepare(mockItems[0].photoId).test {}

        coVerify(exactly = 1) { detailRepository.isItemFavorite(mockItems[0].photoId) }
        confirmVerified(detailRepository)
      }
}
