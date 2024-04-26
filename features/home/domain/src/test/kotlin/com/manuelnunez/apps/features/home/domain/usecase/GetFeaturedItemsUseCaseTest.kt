package com.manuelnunez.apps.features.home.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class GetFeaturedItemsUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val homeRepository = mockk<HomeRepository>()
  private lateinit var useCase: GetFeaturedItemsUseCase

  @BeforeEach
  fun setUp() {
    useCase =
        GetFeaturedItemsUseCase(homeRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `call GetFeaturedItemsUseCase invokes getFeatureItems from repository`() =
      mockkAllExtension.runTest {
        useCase.prepare(Unit).test {}

        verify(exactly = 1) { homeRepository.getFeaturedItems() }
        confirmVerified(homeRepository)
      }
}
