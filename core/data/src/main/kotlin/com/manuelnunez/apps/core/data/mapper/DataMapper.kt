package com.manuelnunez.apps.core.data.mapper

import com.manuelnunez.apps.core.services.dto.PhotoDTO
import com.manuelnunez.apps.core.services.dto.PhotoSrcDTO
import com.manuelnunez.apps.core.services.dto.SearchResponseDTO
import com.manuelnunez.apps.features.home.domain.model.Photo
import com.manuelnunez.apps.features.home.domain.model.PhotoSrc
import com.manuelnunez.apps.features.home.domain.model.SearchResponse

fun SearchResponseDTO.toSearchResponse() =
    SearchResponse(
        totalResult = totalResult, page = page, perPage = perPage, photos = photos.toPhotos())

fun List<PhotoDTO>.toPhotos(): List<Photo> = map { it.photo() }

fun PhotoDTO.photo() =
    Photo(
        id = id,
        width = width,
        height = height,
        url = url,
        photographer = photographer,
        photographerUrl = photographerUrl,
        photographerId = photographerId,
        avgColor = avgColor,
        src = src.toPhotoSrc(),
        liked = liked,
        alt = alt)

fun PhotoSrcDTO.toPhotoSrc(): PhotoSrc =
    PhotoSrc(
        original = original,
        large2x = large2x,
        large = large,
        medium = medium,
        small = small,
        portrait = portrait,
        landscape = landscape,
        tiny = tiny)
