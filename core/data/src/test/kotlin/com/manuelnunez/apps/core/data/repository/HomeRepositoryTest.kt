package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.core.data.datasource.remote.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.utils.mockPexelsSearchResponseDTO
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeRepositoryTest {
  private lateinit var repository: HomeRepository

  private val remoteDataSource = mockk<PexelsCatsRemoteDataSource>()

  @BeforeEach
  fun setUp() {
    repository = HomeRepositoryImpl(remoteDataSource)
  }

  @AfterEach
  fun tearDown() {
    clearAllMocks()
  }

  @Test
  fun `GIVEN getPopularItems call, WHEN success, THEN return 10 shuffled items`() {
    every { remoteDataSource.getItems() } returns eitherSuccess(mockPexelsSearchResponseDTO)

    val itemsResponse = repository.getPopularItems()
    assertTrue(itemsResponse is Either.Success)

    itemsResponse.fold(
        success = { items ->
          assertEquals(10, items.size)
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[0].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[1].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[2].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[3].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[4].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[5].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[6].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[7].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[8].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[9].photoId })
        },
        error = {})
  }

  @Test
  fun `GIVEN getPopularItems call, WHEN failure, THEN return 10 shuffled items`() {
    every { remoteDataSource.getItems() } returns eitherError(ServiceError("", 0, emptyMap()))

    val itemsResponse = repository.getPopularItems()
    assertTrue(itemsResponse is Either.Error)

    itemsResponse.fold(success = {}, error = { assertEquals(ErrorModel.ServiceError, it) })
  }

  @Test
  fun `GIVEN getFeaturedItems call, THEN return 5 shuffled items`() {
    every { remoteDataSource.getItems() } returns eitherSuccess(mockPexelsSearchResponseDTO)

    val itemsResponse = repository.getFeaturedItems()

    itemsResponse.fold(
        success = { items ->
          assertEquals(5, items.size)
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[0].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[1].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[2].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[3].photoId })
          assertTrue(
              mockPexelsSearchResponseDTO.photos.any { it.id.toString() == items[4].photoId })
        },
        error = {})
  }

  @Test
  fun `GIVEN getFeaturedItems call, WHEN failure, THEN return 5 shuffled items`() {
    every { remoteDataSource.getItems() } returns eitherError(ServiceError("", 0, emptyMap()))

    val itemsResponse = repository.getFeaturedItems()
    assertTrue(itemsResponse is Either.Error)

    itemsResponse.fold(success = {}, error = { assertEquals(ErrorModel.ServiceError, it) })
  }
}
