package com.manuelnunez.apps.features.home.domain.repository

import com.manuelnunez.apps.features.home.domain.model.SearchResponse

interface HomeRepository {
  fun search(): SearchResponse
}
