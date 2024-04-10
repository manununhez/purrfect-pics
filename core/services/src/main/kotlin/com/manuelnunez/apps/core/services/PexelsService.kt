package com.manuelnunez.apps.core.services

import com.manuelnunez.apps.core.services.dto.SearchResponseDTO
import retrofit2.Call
import retrofit2.http.GET

interface PexelsService {

  @GET("search?query=cats") fun search(): Call<SearchResponseDTO>
}
