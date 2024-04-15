package com.manuelnunez.apps.core.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.manuelnunez.apps.core.data.datasource.PexeelsCatsPagingSource
import com.manuelnunez.apps.core.data.utils.mockItems
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SeeMoreRepositoryTest {
  //  @RegisterExtension
  //  private val mainDispatcherRule = MainDispatcherRule()
  //  @RegisterExtension
  //  private val unMockkAllExtension = UnMockkAllRule()

  private lateinit var repository: SeeMoreRepository

  private val pagingSource = mockk<PexeelsCatsPagingSource>(relaxed = true)

  @BeforeEach
  fun setUp() {
    repository = SeeMoreRepositoryImpl(pagingSource)
  }

  @Test
  fun `GIVEN getAllItems call, WHEN success, THEN return items`() {
    coEvery { pagingSource.load(any()) } returns
        PagingSource.LoadResult.Page(data = mockItems, prevKey = null, nextKey = null)

    val response = runBlocking { repository.getAllItems() }
    val expectingResult = PagingData.from(mockItems)

    response.map { assertEquals(expectingResult, it) }
  }
}
