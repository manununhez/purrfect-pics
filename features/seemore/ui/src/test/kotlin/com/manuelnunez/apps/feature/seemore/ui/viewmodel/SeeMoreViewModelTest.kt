package com.manuelnunez.apps.feature.seemore.ui.viewmodel

import androidx.paging.PagingData
import com.manuelnunez.apps.core.common.test.MockkAllRule
import com.manuelnunez.apps.core.common.test.UnMockkAllRule
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.feature.seemore.domain.usecase.GetAllItemUseCase
import com.manuelnunez.apps.feature.seemore.ui.SeeMoreViewModel
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.properties.Delegates

@OptIn(ExperimentalCoroutinesApi::class)
class SeeMoreViewModelTest {

  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val getAllItemUseCase = mockk<GetAllItemUseCase>()

  private var viewModel: SeeMoreViewModel by Delegates.notNull()

  @Test
  fun `WHEN viewmodel init, THEN getAllItemUseCase is called`() =
      mockkAllExtension.runTest {
        val pagingData = PagingData.from(mockItems)

        every { getAllItemUseCase.prepare(Unit) } returns flow { emit(pagingData) }

        viewModel = SeeMoreViewModel(getAllItemUseCase)

        verify(exactly = 1) { getAllItemUseCase.prepare(Unit) }
        confirmVerified(getAllItemUseCase)
      }

  private val mockItems = listOf(Item("1", "imageUrl1", "thumbnailUrl1", "description1"))
}
