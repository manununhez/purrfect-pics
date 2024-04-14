package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.mapper.toItems
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl
@Inject
constructor(private val remoteDataSource: PexelsCatsRemoteDataSource) : HomeRepository {

  override fun getAllItems() =
      remoteDataSource
          .getItems()
          .fold(
              success = { eitherSuccess(it.toItems()) },
              error = { eitherError(ErrorModel.ServiceError) })

  override fun getPopularItems() =
      remoteDataSource
          .getItems()
          .fold(
              success = { eitherSuccess(it.toItems().shuffled().take(10)) },
              error = { eitherError(ErrorModel.ServiceError) })

  override fun getFeaturedItems() =
      remoteDataSource
          .getItems()
          .fold(
              success = { eitherSuccess(it.toItems().shuffled().take(5)) },
              error = { eitherError(ErrorModel.ServiceError) })
}
