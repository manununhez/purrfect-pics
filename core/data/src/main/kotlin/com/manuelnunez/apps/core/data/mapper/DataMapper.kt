package com.manuelnunez.apps.core.data.mapper

import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import com.manuelnunez.apps.core.services.dto.PexelsSearchResponseDTO

fun PexelsSearchResponseDTO.toItems() =
    photos.map {
      Item(
          photoId = it.id.toString(),
          description = it.alt,
          imageUrl = it.src.original,
          thumbnailUrl = it.src.portrait)
    }

fun List<CataasResponseDTO>.toItems(): List<Item> = map {
  Item(
      photoId = it.catId,
      description = it.description,
      imageUrl = it.url,
      thumbnailUrl = it.thumbnailUrl)
}
