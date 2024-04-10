package com.manuelnunez.apps.core.data

import com.manuelnunez.apps.core.data.datasource.CatsRemoteDataSource
import com.manuelnunez.apps.core.data.mapper.toSearchResponse
import com.manuelnunez.apps.features.home.domain.model.SearchResponse
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: CatsRemoteDataSource) :
    HomeRepository {

  override fun search(): SearchResponse = // searchModel
  remoteDataSource.search().toSearchResponse()
}
