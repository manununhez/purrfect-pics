package com.manuelnunez.apps.core.services.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CatImage(
    @SerializedName("_id") val catId: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>
) {
  val url: String
    get() = "https://cataas.com/cat/$catId"

  val thumbnailUrl: String
    get() = "https://cataas.com/cat/$catId?type=small"
}
