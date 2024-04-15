package com.manuelnunez.apps.feature.seemore.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MainDispatcherRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import com.manuelnunez.apps.feature.seemore.domain.usecase.GetAllItemUseCase
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
class GetAllItemUseCaseTest {
  @RegisterExtension private val mainDispatcherRule = MainDispatcherRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val seeMoreRepository = mockk<SeeMoreRepository>()
  private lateinit var useCase: GetAllItemUseCase

  @BeforeEach
  fun setUp() {
    useCase = GetAllItemUseCase(seeMoreRepository, mainDispatcherRule.testDispatcherProvider)
  }

  @Test
  fun `call GetItemUseCase invokes getFeatureItems from repository`() =
      mainDispatcherRule.runTest {
        every { seeMoreRepository.getAllItems() } returns flow { emit(PagingData.empty()) }

        useCase.prepare(Unit).test {}

        verify(exactly = 1) { seeMoreRepository.getAllItems() }
        confirmVerified(seeMoreRepository)
      }
}
