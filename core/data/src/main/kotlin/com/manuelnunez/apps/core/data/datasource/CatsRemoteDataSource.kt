package com.manuelnunez.apps.core.data.datasource

import com.manuelnunez.apps.core.services.dto.SearchResponseDTO

interface CatsRemoteDataSource {
  fun search(): SearchResponseDTO
}
