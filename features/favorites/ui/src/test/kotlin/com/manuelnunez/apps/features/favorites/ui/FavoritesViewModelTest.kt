package com.manuelnunez.apps.features.favorites.ui

import app.cash.turbine.test
import com.manuelnunez.apps.core.common.test.MockkAllRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.domain.usecase.GetFavoritesUseCase
import com.manuelnunez.apps.features.favorites.ui.FavoritesViewModel.FavoriteItemsState
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
class FavoritesViewModelTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val getFavoritesUseCase = mockk<GetFavoritesUseCase>()

  private var viewModel: FavoritesViewModel by Delegates.notNull()

  @Test
  fun `GIVEN viewmodel init, WHEN onSuccess, THEN set state with favorite items`() =
      mockkAllExtension.runTest {
        every { getFavoritesUseCase.prepare(Unit) } returns flow { emit(mockPhotos) }

        viewModel = FavoritesViewModel(getFavoritesUseCase)

        viewModel.favoriteItemsState.test {
          // GIVEN viewModel INIT

          assertTrue(awaitItem() is FavoriteItemsState.Idle)
          assertTrue(awaitItem() is FavoriteItemsState.Loading)
          assertEquals(FavoriteItemsState.ShowList(mockPhotos), awaitItem())

          cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) { getFavoritesUseCase.prepare(Unit) }
        confirmVerified(getFavoritesUseCase)
      }

  @Test
  fun `GIVEN viewmodel init, WHEN exception catch, THEN set state with error`() =
      mockkAllExtension.runTest {
        every { getFavoritesUseCase.prepare(Unit) } returns flow { throw Exception() }

        viewModel = FavoritesViewModel(getFavoritesUseCase)

        viewModel.favoriteItemsState.test {
          // GIVEN viewModel INIT

          assertTrue(awaitItem() is FavoriteItemsState.Idle)
          assertTrue(awaitItem() is FavoriteItemsState.Loading)
          assertEquals(FavoriteItemsState.Error, awaitItem())

          cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) { getFavoritesUseCase.prepare(Unit) }
        confirmVerified(getFavoritesUseCase)
      }

  private val mockPhotos: List<Item> =
      List(20) { index ->
        val id = (index + 1).toString()
        Item(
            photoId = id,
            imageUrl = "https://example.com/photo$id",
            thumbnailUrl = "https://example.com/photo$id/small",
            description = "This is a description for item $id")
      }
}
