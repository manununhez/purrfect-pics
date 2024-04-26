package com.manuelnunez.apps.features.home.ui.viewmodel

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.home.domain.usecase.GetFeaturedItemsUseCase
import com.manuelnunez.apps.features.home.domain.usecase.GetPopularItemsUseCase
import com.manuelnunez.apps.features.home.ui.HomeViewModel
import com.manuelnunez.apps.features.home.ui.HomeViewModel.FeaturedItemsState
import com.manuelnunez.apps.features.home.ui.HomeViewModel.PopularItemsState
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.properties.Delegates

class HomeViewModelTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val getFeaturedItemsUseCase = mockk<GetFeaturedItemsUseCase>()
  private val getPopularItemsUseCase = mockk<GetPopularItemsUseCase>()

  private var viewModel: HomeViewModel by Delegates.notNull()

  @Test
  fun `GIVEN viewmodel init, WHEN onSuccess, THEN set state with popular and featured items`() =
      mockkAllExtension.runTest {
        every { getFeaturedItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherSuccess(mockItems)) }
        every { getPopularItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherSuccess(mockItems)) }

        viewModel = HomeViewModel(getFeaturedItemsUseCase, getPopularItemsUseCase)

        viewModel.state.test {
          // GIVEN viewModel INIT

          awaitItem().apply {
            assertTrue(featuredItemsState is FeaturedItemsState.Idle)
            assertTrue(popularItemsState is PopularItemsState.Idle)
          }

          awaitItem().apply {
            assertEquals(FeaturedItemsState.ShowList(mockItems), featuredItemsState)
            assertEquals(PopularItemsState.ShowList(mockItems), popularItemsState)
          }

          cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) {
          getFeaturedItemsUseCase.prepare(Unit)

          getPopularItemsUseCase.prepare(Unit)
        }
        confirmVerified(getFeaturedItemsUseCase, getPopularItemsUseCase)
      }

  @Test
  fun `GIVEN viewmodel init, WHEN onFailure, THEN set state with ERROR`() =
      mockkAllExtension.runTest {
        every { getFeaturedItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherError(ErrorModel.ServiceError)) }
        every { getPopularItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherError(ErrorModel.ServiceError)) }

        viewModel = HomeViewModel(getFeaturedItemsUseCase, getPopularItemsUseCase)

        viewModel.state.test {
          // GIVEN viewModel INIT

          awaitItem().apply {
            assertTrue(featuredItemsState is FeaturedItemsState.Idle)
            assertTrue(popularItemsState is PopularItemsState.Idle)
          }

          awaitItem().apply {
            assertEquals(FeaturedItemsState.Error, featuredItemsState)
            assertEquals(PopularItemsState.Error, popularItemsState)
          }

          cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) {
          getFeaturedItemsUseCase.prepare(Unit)
          getPopularItemsUseCase.prepare(Unit)
        }
        confirmVerified(getFeaturedItemsUseCase, getPopularItemsUseCase)
      }

  @Test
  fun `GIVEN viewmodel init, WHEN exception catch, THEN set state with error`() =
      mockkAllExtension.runTest {
        every { getFeaturedItemsUseCase.prepare(Unit) } returns flow { throw Exception() }
        every { getPopularItemsUseCase.prepare(Unit) } returns flow { throw Exception() }

        viewModel = HomeViewModel(getFeaturedItemsUseCase, getPopularItemsUseCase)

        viewModel.state.test {
          // GIVEN viewModel INIT

          awaitItem().apply {
            assertTrue(featuredItemsState is FeaturedItemsState.Idle)
            assertTrue(popularItemsState is PopularItemsState.Idle)
          }

          awaitItem().apply {
            assertEquals(FeaturedItemsState.Error, featuredItemsState)
            assertEquals(PopularItemsState.Error, popularItemsState)
          }

          cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) {
          getFeaturedItemsUseCase.prepare(Unit)
          getPopularItemsUseCase.prepare(Unit)
        }
        confirmVerified(getFeaturedItemsUseCase, getPopularItemsUseCase)
      }
}
