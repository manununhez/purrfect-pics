package com.manuelnunez.apps.core.services.service

import com.manuelnunez.apps.core.services.dto.PexelsSearchResponseDTO
import com.manuelnunez.apps.core.services.dto.PhotoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsService {

  @GET("search")
  fun searchCats(
      @Query("query") query: String = "cats",
      @Query("page") page: Int = 0,
      @Query("per_page") perPage: Int = 40
  ): Call<PexelsSearchResponseDTO>

  @GET("photos/{id}") fun searchCatsById(@Path("id") id: Long): Call<PhotoDTO>
}
