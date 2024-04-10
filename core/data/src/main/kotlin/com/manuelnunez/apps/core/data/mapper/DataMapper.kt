package com.manuelnunez.apps.core.data.mapper

import com.manuelnunez.apps.core.services.dto.CatImage
import com.manuelnunez.apps.core.services.dto.SearchResponseDTO
import com.manuelnunez.apps.features.home.domain.model.Item

fun SearchResponseDTO.toItems() =
    photos.map {
      Item(description = it.alt, imageUrl = it.src.original, thumbnailUrl = it.src.portrait)
    }

fun List<CatImage>.toItems(): List<Item> = map {
  Item(description = it.tags.toString(), imageUrl = it.url, thumbnailUrl = it.thumbnailUrl)
}
