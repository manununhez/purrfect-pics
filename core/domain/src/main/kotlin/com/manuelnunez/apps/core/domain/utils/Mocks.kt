package com.manuelnunez.apps.core.domain.utils

import com.manuelnunez.apps.core.domain.model.Item

val mockItems: List<Item> =
    List(20) { index ->
      val id = (index + 1).toString()
      Item(
          photoId = id,
          imageUrl = "https://example.com/photo$id",
          thumbnailUrl = "https://example.com/photo$id/small",
          description = "This is a description for item $id")
    }

val mockPopularPhotos = mockItems.shuffled().take(10)

val mockFeaturedPhotos = mockItems.shuffled().take(5)
