package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.data.datasource.remote.PexelsCatsRemoteDataSource
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
import javax.inject.Inject

class SeeMoreRepositoryImpl
@Inject
constructor(private val remoteDataSource: PexelsCatsRemoteDataSource) : SeeMoreRepository {

  override fun getAllItems() = remoteDataSource.getAllItems()
}
