package com.manuelnunez.apps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val photoId: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val description: String
) : Parcelable
