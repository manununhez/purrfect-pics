package com.manuelnunez.apps.feature.seemore.domain.repository

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.domain.model.Item

interface SeeMoreRepository {
  fun getAllItems(): Either<List<Item>, ErrorModel>
}
