package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.utils.mockPexelsSearchResponseDTO
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
    every { remoteDataSource.getItems() } returns eitherSuccess(mockPexelsSearchResponseDTO)

    val itemsResponse = repository.getAllItems()
    assertTrue(itemsResponse is Either.Success)

    itemsResponse.fold(
        success = { items ->
          assertEquals(mockPexelsSearchResponseDTO.photos.size, items.size)
          items[0].apply {
            assertEquals(mockPexelsSearchResponseDTO.photos[0].src.original, imageUrl)
            assertEquals(mockPexelsSearchResponseDTO.photos[0].src.portrait, thumbnailUrl)
            assertEquals(mockPexelsSearchResponseDTO.photos[0].alt, description)
          }
        },
        error = {})
  }

  @Test
  fun `GIVEN getAllItems call, WHEN failure, THEN return serviceError`() {
    every { remoteDataSource.getItems() } returns eitherError(ServiceError("", 0, emptyMap()))

    val itemsResponse = repository.getAllItems()
    assertTrue(itemsResponse is Either.Error)

    itemsResponse.fold(success = {}, error = { assertEquals(ErrorModel.ServiceError, it) })
  }
}
