package com.manuelnunez.apps.core.data.repository.fake

import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class FakeHomeRepository @Inject constructor() : HomeRepository {

  override fun getPopularItems() = eitherSuccess(mockItems.shuffled().take(10))

  override fun getFeaturedItems() = eitherSuccess(mockItems.shuffled().take(5))
}
