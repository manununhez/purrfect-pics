package com.manuelnunez.apps.core.datastore.proto.serializer

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.manuelnunez.apps.core.datastore.proto.ItemList
import java.io.InputStream
import java.io.OutputStream

class ItemListSerializer : Serializer<ItemList> {
  override val defaultValue: ItemList = ItemList.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): ItemList {
    try {
      return ItemList.parseFrom(input)
    } catch (exception: InvalidProtocolBufferException) {
      throw IllegalArgumentException("Error parsing ItemList", exception)
    }
  }

  override suspend fun writeTo(t: ItemList, output: OutputStream) = t.writeTo(output)
}
