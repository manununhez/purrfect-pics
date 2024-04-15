package com.manuelnunez.apps.core.services.service

import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import com.manuelnunez.apps.core.services.dto.PhotoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CataasService {

  @GET("api/cats")
  fun searchCats(
      @Query("skip") skip: Int = 0,
      @Query("limit") limit: Int = 10
  ): Call<List<CataasResponseDTO>>

  @GET("api/cats")
  suspend fun searchCatsPaginated(
      @Query("skip") skip: Int = 0,
      @Query("limit") limit: Int = 10
  ): List<CataasResponseDTO>

  @GET("photos/{id}") fun searchCatsById(@Path("id") id: Long): Call<PhotoDTO>
}
