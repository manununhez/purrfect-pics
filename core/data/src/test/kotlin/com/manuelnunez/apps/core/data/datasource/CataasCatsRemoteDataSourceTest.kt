package com.manuelnunez.apps.core.data.datasource

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.data.mapper.toItems
import com.manuelnunez.apps.core.data.utils.mockCataasResponseDTOS
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.executors.toServiceResponse
import com.manuelnunez.apps.core.services.service.CataasService
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class CataasCatsRemoteDataSourceTest {
  private val servicesExecutor: ServicesExecutor = mockk()
  private val apiService: CataasService = mockk()
  private val mockServiceError = mockk<ServiceError>()

  private lateinit var remoteDataSource: CataasCatsRemoteDataSource

  @BeforeEach
  fun setup() {
    remoteDataSource = CataasCatsRemoteDataSourceImpl(servicesExecutor, apiService)
  }

  @Test
  fun `when getItems is called successfully, then returns PexelsSearchResponseDTO`() {
    val responseSuccess = Response.success(mockCataasResponseDTOS)
    val resultSuccess = eitherSuccess(responseSuccess.toServiceResponse())

    every { apiService.searchCats().execute() } returns responseSuccess
    every {
      servicesExecutor.execute(any<RetrofitServiceRequest<List<CataasResponseDTO>>>())
    } returns resultSuccess

    val result = remoteDataSource.getItems()

    Assertions.assertTrue(result is Either.Success)
    Assertions.assertEquals(eitherSuccess(mockCataasResponseDTOS), result)
    verify(exactly = 1) {
      apiService.searchCats()
      servicesExecutor.execute(any<RetrofitServiceRequest<List<CataasResponseDTO>>>())
    }
    confirmVerified(apiService, servicesExecutor)
  }

  @Test
  fun `when getItems call fails, then returns ServiceError`() {
    val responseSuccess = Response.success(mockCataasResponseDTOS)
    val errorSuccess = eitherError(mockServiceError)

    every { apiService.searchCats().execute() } returns responseSuccess
    every {
      servicesExecutor.execute(any<RetrofitServiceRequest<List<CataasResponseDTO>>>())
    } returns errorSuccess

    val result = remoteDataSource.getItems()

    Assertions.assertTrue(result is Either.Error)
    Assertions.assertEquals(eitherError(mockServiceError), result)
    verify(exactly = 1) {
      apiService.searchCats()
      servicesExecutor.execute(any<RetrofitServiceRequest<List<CataasResponseDTO>>>())
    }
    confirmVerified(apiService, servicesExecutor)
  }

  @Test
  fun `when getAllItems is called, then returns paginated Items`() = runTest {
    val pagingData: PagingData<Item> = PagingData.from(mockCataasResponseDTOS.toItems())

    coEvery { apiService.searchCatsPaginated(skip = any()) } returns mockCataasResponseDTOS

    val resultFlow = remoteDataSource.getAllItems()

    resultFlow.test {
      pagingData.map { expected -> awaitItem().map { Assertions.assertEquals(expected, it) } }
      cancelAndIgnoreRemainingEvents()
    }
  }
}
