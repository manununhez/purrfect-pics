package com.manuelnunez.apps.features.home.ui.viewmodel

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.test.MainDispatcherRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.features.home.domain.usecase.GetFeaturedItemsUseCase
import com.manuelnunez.apps.features.home.domain.usecase.GetPopularItemsUseCase
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.FeaturedItemsState
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.PopularItemsState
import com.manuelnunez.apps.features.home.ui.utils.mockPhotos
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.properties.Delegates

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {
  @RegisterExtension private val mainDispatcherRule = MainDispatcherRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val getFeaturedItemsUseCase = mockk<GetFeaturedItemsUseCase>()
  private val getPopularItemsUseCase = mockk<GetPopularItemsUseCase>()

  private var viewModel: HomeScreenViewModel by Delegates.notNull()

  @Test
  fun `GIVEN viewmodel init, WHEN onSuccess, THEN set state with popular and featured items`() =
      mainDispatcherRule.runTest {
        every { getFeaturedItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherSuccess(mockPhotos)) }
        every { getPopularItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherSuccess(mockPhotos)) }

        viewModel = HomeScreenViewModel(getFeaturedItemsUseCase, getPopularItemsUseCase)

        viewModel.state.test {
          // GIVEN viewModel INIT

          awaitItem().apply {
            assertTrue(featuredItemsState is FeaturedItemsState.Idle)
            assertTrue(popularItemsState is PopularItemsState.Idle)
          }

          awaitItem().apply {
            assertEquals(FeaturedItemsState.ShowList(mockPhotos), featuredItemsState)
            assertEquals(PopularItemsState.ShowList(mockPhotos), popularItemsState)
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
      mainDispatcherRule.runTest {
        every { getFeaturedItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherError(ErrorModel.ServiceError)) }
        every { getPopularItemsUseCase.prepare(Unit) } returns
            flow { emit(eitherError(ErrorModel.ServiceError)) }

        viewModel = HomeScreenViewModel(getFeaturedItemsUseCase, getPopularItemsUseCase)

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
