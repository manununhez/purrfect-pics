package com.manuelnunez.apps.core.services.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CataasResponseDTO(
    @SerializedName("_id") val catId: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>
) {
  val url: String
    get() = "https://cataas.com/cat/$catId"

  val thumbnailUrl: String
    get() = "https://cataas.com/cat/$catId?type=small"

  val description: String
    get() = tags.joinToString(separator = " ") { "#$it" }
}
