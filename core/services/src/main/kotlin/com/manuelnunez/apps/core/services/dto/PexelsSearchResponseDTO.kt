package com.manuelnunez.apps.core.services.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SearchResponseDTO(
    @SerializedName("total_results") val totalResult: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("photos") val photos: List<PhotoDTO>
)

@Keep
data class PhotoDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("photographer_url") val photographerUrl: String,
    @SerializedName("photographer_id") val photographerId: Int,
    @SerializedName("avg_color") val avgColor: String,
    @SerializedName("src") val src: PhotoSrcDTO,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("alt") val alt: String
)

@Keep
data class PhotoSrcDTO(
    @SerializedName("original") val original: String,
    @SerializedName("large2x") val large2x: String,
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("small") val small: String,
    @SerializedName("portrait") val portrait: String,
    @SerializedName("landscape") val landscape: String,
    @SerializedName("tiny") val tiny: String
)
