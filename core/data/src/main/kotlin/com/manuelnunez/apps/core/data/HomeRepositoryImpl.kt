package com.manuelnunez.apps.core.data

import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.mapper.toItems
import com.manuelnunez.apps.features.home.domain.model.Item
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl
@Inject
constructor(private val remoteDataSource: PexelsCatsRemoteDataSource) : HomeRepository {

  override fun getAllItems(): List<Item> = remoteDataSource.getItems().toItems()

  override fun getPopularItems(): List<Item> =
      remoteDataSource.getItems().toItems().shuffled().take(10)

  override fun getFeaturedItems(): List<Item> =
      remoteDataSource.getItems().toItems().shuffled().take(5)
}
