package com.manuelnunez.apps.features.detail.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.domain.model.toEncodedString
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.detail.domain.usecase.GetFavoriteStatusUseCase
import com.manuelnunez.apps.features.detail.domain.usecase.RemoveFavoritesUseCase
import com.manuelnunez.apps.features.detail.domain.usecase.SaveFavoritesUseCase
import com.manuelnunez.apps.features.detail.ui.navigation.DETAIL_ITEM
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.properties.Delegates

class DetailViewModelTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val saveFavoritesUseCase = mockk<SaveFavoritesUseCase>()
  private val removeFavoritesUseCase = mockk<RemoveFavoritesUseCase>()
  private val getFavoriteStatusUseCase = mockk<GetFavoriteStatusUseCase>()
  private val savedStateHandle = mockk<SavedStateHandle>()
  private val selectedItemStringFlow = MutableStateFlow(mockItems[0].toEncodedString())

  private var viewModel: DetailViewModel by Delegates.notNull()

  @BeforeEach
  fun setup() {
    every { savedStateHandle.getStateFlow(DETAIL_ITEM, "") } returns selectedItemStringFlow

    every { getFavoriteStatusUseCase.prepare(any()) } returns flow { emit(true) }

    viewModel =
        DetailViewModel(
            saveFavoritesUseCase,
            removeFavoritesUseCase,
            getFavoriteStatusUseCase,
            savedStateHandle)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `on Init is called checkFavorite and sets isItemFavorite correctly`() =
      mockkAllExtension.runTest {
        val isFavorite = true

        viewModel.state.test {
          // GIVEN viewModel INIT

          assertEquals(DetailViewModel.DetailUiState.Empty, awaitItem())
          assertEquals(
              DetailViewModel.DetailUiState(isFavorite = isFavorite, item = mockItems[0]),
              awaitItem())

          cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) { getFavoriteStatusUseCase.prepare(any()) }
        confirmVerified(getFavoriteStatusUseCase)
      }

  @Test
  fun `favorite calls removeFavorite if isItemFavorite is true`() {
    // Given
    every { removeFavoritesUseCase.prepare(any()) } returns flow { emit(Unit) }

    viewModel =
        DetailViewModel(
            saveFavoritesUseCase,
            removeFavoritesUseCase,
            getFavoriteStatusUseCase,
            savedStateHandle)

    viewModel.isItemFavorite.value = true

    // When
    viewModel.favorite()

    // Then
    verify(exactly = 1) { viewModel.removeFavorite(any()) }
    verify(exactly = 0) { viewModel.saveFavorite(any()) }
  }

  @Test
  fun `favorite calls saveFavorites if isItemFavorite is false`() {
    // Given
    every { saveFavoritesUseCase.prepare(any()) } returns flow { emit(Unit) }

    viewModel =
        DetailViewModel(
            saveFavoritesUseCase,
            removeFavoritesUseCase,
            getFavoriteStatusUseCase,
            savedStateHandle)

    viewModel.isItemFavorite.value = false

    // When
    viewModel.favorite()

    // Then
    verify(exactly = 0) { viewModel.removeFavorite(any()) }
    verify(exactly = 1) { viewModel.saveFavorite(any()) }
  }
}
