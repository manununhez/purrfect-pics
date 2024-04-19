package com.manuelnunez.apps.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Serializable
data class Item(
    val photoId: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val description: String
) {
  companion object {
    val empty = Item(photoId = "", imageUrl = "", thumbnailUrl = "", description = "")
  }
}

fun Item.toEncodedString(): String = URLEncoder.encode(Json.encodeToString(this), "UTF-8")

fun String.toDecodedItem(): Item = Json.decodeFromString<Item>(URLDecoder.decode(this, "UTF-8"))
