package com.manuelnunez.apps.feature.seemore.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MockkAllRule
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
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val seeMoreRepository = mockk<SeeMoreRepository>()
  private lateinit var useCase: GetAllItemUseCase

  @BeforeEach
  fun setUp() {
    useCase = GetAllItemUseCase(seeMoreRepository, mockkAllExtension.testCoroutineDispatcherProvider)
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
