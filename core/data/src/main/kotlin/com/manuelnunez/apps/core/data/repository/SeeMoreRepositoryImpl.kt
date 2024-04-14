package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.core.data.datasource.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.core.data.mapper.toItems
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import javax.inject.Inject

class SeeMoreRepositoryImpl
@Inject
constructor(private val remoteDataSource: PexelsCatsRemoteDataSource) : SeeMoreRepository {

  override fun getAllItems() =
      remoteDataSource
          .getItems()
          .fold(
              success = { eitherSuccess(it.toItems()) },
              error = { eitherError(ErrorModel.ServiceError) })
}
