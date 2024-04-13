package com.manuelnunez.apps.features.home.domain.repository

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.features.home.domain.model.HomeErrorModel
import com.manuelnunez.apps.features.home.domain.model.Item

interface HomeRepository {
  fun getAllItems(): Either<List<Item>, HomeErrorModel>

  fun getPopularItems(): Either<List<Item>, HomeErrorModel>

  fun getFeaturedItems(): Either<List<Item>, HomeErrorModel>
}
