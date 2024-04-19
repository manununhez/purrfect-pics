package com.manuelnunez.apps.features.detail.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MockkAllRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
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

  @Test
  fun `call GetFavoriteStatusUseCase invokes isItemFavorite from repository`() =
      mockkAllExtension.runTest {
        every { detailRepository.isItemFavorite(mockItem.photoId) } returns flow { emit(false) }
        useCase.prepare(mockItem.photoId).test {}

        coVerify(exactly = 1) { detailRepository.isItemFavorite(mockItem.photoId) }
        confirmVerified(detailRepository)
      }
}
