package com.manuelnunez.apps.core.data.utils

import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import com.manuelnunez.apps.core.services.dto.PexelsSearchResponseDTO
import com.manuelnunez.apps.core.services.dto.PhotoDTO
import com.manuelnunez.apps.core.services.dto.PhotoSrcDTO

val mockPhotosDTO: List<PhotoDTO> =
    List(20) { index ->
      val id = index + 1
      val photographer = if (id % 2 == 0) "John Doe" else "Jane Smith"
      val photographerId = if (id % 2 == 0) 123 else 456
      val liked = id % 2 == 0
      val alt = if (id % 2 == 0) "A beautiful photo" else "Another beautiful photo"

      PhotoDTO(
          id = id,
          width = if (id % 2 == 0) 1920 else 1280,
          height = if (id % 2 == 0) 1080 else 720,
          url = "https://example.com/photo$id",
          photographer = photographer,
          photographerUrl = "https://example.com/photographer$id",
          photographerId = photographerId,
          avgColor = if (id % 2 == 0) "#FFFFFF" else "#000000",
          src =
              PhotoSrcDTO(
                  original = "https://example.com/photo$id/original",
                  large2x = "https://example.com/photo$id/large2x",
                  large = "https://example.com/photo$id/large",
                  medium = "https://example.com/photo$id/medium",
                  small = "https://example.com/photo$id/small",
                  portrait = "https://example.com/photo$id/portrait",
                  landscape = "https://example.com/photo$id/landscape",
                  tiny = "https://example.com/photo$id/tiny"),
          liked = liked,
          alt = alt)
    }

val mockPexelsSearchResponseDTO =
    PexelsSearchResponseDTO(totalResult = 10, page = 1, perPage = 20, photos = mockPhotosDTO)

val mockCataasResponseDTOS =
    listOf(
        CataasResponseDTO(
            catId = "123456",
            mimetype = "image/jpeg",
            size = 1024,
            tags = listOf("cute", "kitten", "fluffy")),
        CataasResponseDTO(
            catId = "789012",
            mimetype = "image/jpeg",
            size = 2048,
            tags = listOf("adorable", "furry", "playful")),
        CataasResponseDTO(
            catId = "345678",
            mimetype = "image/jpeg",
            size = 4096,
            tags = listOf("funny", "mischief", "whiskers")))
