package com.manuelnunez.apps.features.seemore.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
import com.manuelnunez.apps.features.seemore.domain.usecase.GetAllItemUseCase
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

class GetAllItemUseCaseTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val seeMoreRepository = mockk<SeeMoreRepository>()
  private lateinit var useCase: GetAllItemUseCase

  @BeforeEach
  fun setUp() {
    useCase =
        GetAllItemUseCase(seeMoreRepository, mockkAllExtension.testCoroutineDispatcherProvider)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `call GetItemUseCase invokes getFeatureItems from repository`() =
      mockkAllExtension.runTest {
        every { seeMoreRepository.getAllItems() } returns flow { emit(PagingData.empty()) }

        useCase.prepare(Unit).test {}

        verify(exactly = 1) { seeMoreRepository.getAllItems() }
        confirmVerified(seeMoreRepository)
      }
}
