package com.papero.minisqlite.domain.mapper

import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse
import com.papero.minisqlite.domain.entities.GoogleRepoEntity

fun GoogleGithubResponse?.toGoogleRepoEntity() : GoogleRepoEntity {
    return GoogleRepoEntity(
        id = this?.id ?: 0,
        name = this?.name,
        linkRepo = this?.linkRepo
    )
}