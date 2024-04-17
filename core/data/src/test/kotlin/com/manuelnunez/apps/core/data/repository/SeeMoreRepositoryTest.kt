package com.manuelnunez.apps.core.data.repository

import androidx.paging.PagingData
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.utils.mockItems
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SeeMoreRepositoryTest {
  private lateinit var repository: SeeMoreRepository

  private val remoteDataSource = mockk<PexelsCatsRemoteDataSource>()

  @BeforeEach
  fun setUp() {
    repository = SeeMoreRepositoryImpl(remoteDataSource)
  }

  @Test
  fun `GIVEN getAllItems call, WHEN success, THEN return items`() {
    val expectedResult = PagingData.from(mockItems)

    every { remoteDataSource.getAllItems() } returns flow { emit(expectedResult) }

    repository.getAllItems().map { assertEquals(expectedResult, it) }

    verify(exactly = 1) { remoteDataSource.getAllItems() }
    confirmVerified(remoteDataSource)
  }
}
