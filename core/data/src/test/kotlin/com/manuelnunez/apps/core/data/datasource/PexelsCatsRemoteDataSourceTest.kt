package com.manuelnunez.apps.core.data.datasource

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.data.mapper.toItems
import com.manuelnunez.apps.core.data.utils.mockPexelsSearchResponseDTO
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.services.dto.PexelsSearchResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.executors.toServiceResponse
import com.manuelnunez.apps.core.services.service.PexelsService
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class PexelsCatsRemoteDataSourceTest {
  private val servicesExecutor: ServicesExecutor = mockk()
  private val apiService: PexelsService = mockk()
  private val mockServiceError = mockk<ServiceError>()

  private lateinit var remoteDataSource: PexelsCatsRemoteDataSource

  @BeforeEach
  fun setup() {
    remoteDataSource = PexelsCatsRemoteDataSourceImpl(servicesExecutor, apiService)
  }

  @Test
  fun `when getItems is called successfully, then returns PexelsSearchResponseDTO`() {
    val responseSuccess = Response.success(mockPexelsSearchResponseDTO)
    val resultSuccess = eitherSuccess(responseSuccess.toServiceResponse())

    every { apiService.searchCats().execute() } returns responseSuccess
    every {
      servicesExecutor.execute(any<RetrofitServiceRequest<PexelsSearchResponseDTO>>())
    } returns resultSuccess

    val result = remoteDataSource.getItems()

    assertTrue(result is Either.Success)
    assertEquals(eitherSuccess(mockPexelsSearchResponseDTO), result)
    verify(exactly = 1) {
      apiService.searchCats()
      servicesExecutor.execute(any<RetrofitServiceRequest<PexelsSearchResponseDTO>>())
    }
    confirmVerified(apiService, servicesExecutor)
  }

  @Test
  fun `when getItems call fails, then returns ServiceError`() {
    val responseSuccess = Response.success(mockPexelsSearchResponseDTO)
    val errorSuccess = eitherError(mockServiceError)

    every { apiService.searchCats().execute() } returns responseSuccess
    every {
      servicesExecutor.execute(any<RetrofitServiceRequest<PexelsSearchResponseDTO>>())
    } returns errorSuccess

    val result = remoteDataSource.getItems()

    assertTrue(result is Either.Error)
    assertEquals(eitherError(mockServiceError), result)
    verify(exactly = 1) {
      apiService.searchCats()
      servicesExecutor.execute(any<RetrofitServiceRequest<PexelsSearchResponseDTO>>())
    }
    confirmVerified(apiService, servicesExecutor)
  }

  @Test
  fun `when getAllItems is called, then returns paginated Items`() = runTest {
    val pagingData: PagingData<Item> = PagingData.from(mockPexelsSearchResponseDTO.toItems())

    coEvery { apiService.searchCatsPaginated(page = any()) } returns mockPexelsSearchResponseDTO

    val resultFlow = remoteDataSource.getAllItems()

    resultFlow.test {
      pagingData.map { expected -> awaitItem().map { assertEquals(expected, it) } }
      cancelAndIgnoreRemainingEvents()
    }
  }
}
