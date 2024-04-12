package com.manuelnunez.apps.features.home.domain.usecase

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MainDispatcherRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class GetItemUseCaseTest {
  @RegisterExtension private val mainDispatcherRule = MainDispatcherRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val homeRepository = mockk<HomeRepository>()
  private lateinit var useCase: GetItemUseCase

  @BeforeEach
  fun setUp() {
    useCase = GetItemUseCase(homeRepository, mainDispatcherRule.testDispatcherProvider)
  }

  @Test
  fun `call GetItemUseCase invokes getFeatureItems from repository`() =
      mainDispatcherRule.runTest {
        useCase.prepare(Unit).test {}

        verify(exactly = 1) { homeRepository.getAllItems() }
        confirmVerified(homeRepository)
      }
}
