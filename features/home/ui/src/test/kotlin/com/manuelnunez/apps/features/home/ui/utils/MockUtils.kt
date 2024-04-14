package com.manuelnunez.apps.features.home.ui.utils

import com.manuelnunez.apps.core.domain.model.Item

val mockPhotos: List<Item> =
    List(20) { index ->
      val id = (index + 1).toString()
      Item(
          photoId = id,
          imageUrl = "https://example.com/photo$id",
          thumbnailUrl = "https://example.com/photo$id/small",
          description = "This is a description for item $id")
    }
