package com.manuelnunez.apps.core.data.mapper

import com.manuelnunez.apps.core.data.utils.mockCataasResponseDTOS
import com.manuelnunez.apps.core.data.utils.mockPexelsSearchResponseDTO
import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataMapperTest {
  @Test
  fun `given SearchResponseDTO, when mapped to domain, then return items`() {
    // GIVEN
    val pexeelsResponse = mockPexelsSearchResponseDTO
    // WHEN
    val items = pexeelsResponse.toItems()
    // THEN
    assertEquals(pexeelsResponse.photos.size, items.size)
    items[0].apply {
      assertEquals(pexeelsResponse.photos[0].id.toString(), photoId)
      assertEquals(pexeelsResponse.photos[0].src.original, imageUrl)
      assertEquals(pexeelsResponse.photos[0].src.portrait, thumbnailUrl)
      assertEquals(pexeelsResponse.photos[0].alt, description)
    }
  }

  @Test
  fun `given CatImage list, when mapped to domain, then return items`() {
    // GIVEN
    val cataasResponse = mockCataasResponseDTOS
    // WHEN
    val items = cataasResponse.toItems()
    // THEN
    assertEquals(cataasResponse.size, items.size)
    items[0].apply {
      assertEquals(cataasResponse[0].catId, photoId)
      assertEquals(cataasResponse[0].url, imageUrl)
      assertEquals(cataasResponse[0].thumbnailUrl, thumbnailUrl)
      assertEquals(cataasResponse[0].description, description)
    }
  }

  @Test
  fun `given CatImage, then derived properties are formed correctly`() {
    val cataasResponse =
        CataasResponseDTO(
            catId = "123456",
            mimetype = "image/jpeg",
            size = 1024,
            tags = listOf("cute", "kitten", "fluffy"))

    assertEquals("https://cataas.com/cat/123456", cataasResponse.url)
    assertEquals("https://cataas.com/cat/123456?type=small", cataasResponse.thumbnailUrl)
    assertEquals("#cute #kitten #fluffy", cataasResponse.description)
  }
}
