package com.manuelnunez.apps.features.home.domain.repository

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.domain.model.Item

interface HomeRepository {
  fun getPopularItems(): Either<List<Item>, ErrorModel>

  fun getFeaturedItems(): Either<List<Item>, ErrorModel>
}
