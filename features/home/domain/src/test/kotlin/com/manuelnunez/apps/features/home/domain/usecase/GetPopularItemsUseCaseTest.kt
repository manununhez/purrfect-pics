package com.manuelnunez.apps.features.home.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MockkAllRule
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class GetPopularItemsUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val homeRepository = mockk<HomeRepository>()
  private lateinit var useCase: GetPopularItemsUseCase

  @BeforeEach
  fun setUp() {
    useCase = GetPopularItemsUseCase(homeRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @Test
  fun `call GetItemUseCase invokes getFeatureItems from repository`() =
      mockkAllExtension.runTest {
        useCase.prepare(Unit).test {}

        verify(exactly = 1) { homeRepository.getPopularItems() }
        confirmVerified(homeRepository)
      }
}
