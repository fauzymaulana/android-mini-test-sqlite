package com.papero.minisqlite.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoogleRepoEntity(
    val id: Int,
    val name: String?,
    val linkRepo: String?
): Parcelable
