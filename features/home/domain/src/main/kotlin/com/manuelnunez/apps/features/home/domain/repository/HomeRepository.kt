package com.manuelnunez.apps.features.home.domain.repository

import com.manuelnunez.apps.features.home.domain.model.Item

interface HomeRepository {
  fun getAllItems(): List<Item>

  fun getPopularItems(): List<Item>

  fun getFeaturedItems(): List<Item>
}
