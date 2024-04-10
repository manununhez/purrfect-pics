package com.manuelnunez.apps.features.home.domain.model

data class SearchResponse(
    val totalResult: Int,
    val page: Int,
    val perPage: Int,
    val photos: List<Photo>
)

data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val photographerId: Int,
    val avgColor: String,
    val src: PhotoSrc,
    val liked: Boolean,
    val alt: String
)

data class PhotoSrc(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)
