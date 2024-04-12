package com.manuelnunez.apps.features.home.ui.utils

import com.manuelnunez.apps.features.home.domain.model.Item

val mockPopularPhotos =
    List(20) { index ->
          val id = (index + 1).toString()
          Item(
              photoId = id,
              imageUrl = "https://example.com/photo$id",
              thumbnailUrl = "https://example.com/photo$id/small",
              description = "This is a description for popular items $id")
        }
        .shuffled()
        .take(10)

val mockFeaturedPhotos =
    List(20) { index ->
          val id = (index + 1).toString()
          Item(
              photoId = id,
              imageUrl = "https://example.com/photo$id",
              thumbnailUrl = "https://example.com/photo$id/small",
              description = "This is a description for featured items $id")
        }
        .shuffled()
        .take(5)
