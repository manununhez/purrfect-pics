package com.manuelnunez.apps.core.datastore.proto.serializer

import com.manuelnunez.apps.core.common.testRule.MockkAllRule
import com.manuelnunez.apps.core.common.testRule.UnMockkAllRule
import com.manuelnunez.apps.core.datastore.proto.ItemList
import com.manuelnunez.apps.core.datastore.proto.itemList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ItemListSerializerTest {
  @RegisterExtension private val mockkAllExtension = MockkAllRule()
  @RegisterExtension private val unMockkAllExtension = UnMockkAllRule()

  private val serializer = ItemListSerializer()

  @Test
  fun `defaultItemList is empty for default`() {
    assertEquals(
        itemList {
          // Default value
        },
        serializer.defaultValue,
    )
  }

  @Test
  fun `read and write ItemList correctly from InputStream`() =
      mockkAllExtension.runTest {
        // Given
        val expectedItemList = ItemList.getDefaultInstance()
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        // When
        serializer.writeTo(expectedItemList, outputStream)
        val actualItemList = serializer.readFrom(inputStream)

        // Then
        assertEquals(expectedItemList, actualItemList)
      }

  @Test
  fun `readFrom throws IllegalArgumentException when parsing fails`() {
    // When, Then
    assertThrows(IllegalArgumentException::class.java) {
      mockkAllExtension.runTest { serializer.readFrom(ByteArrayInputStream(byteArrayOf(0))) }
    }
  }
}
