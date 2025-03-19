package com.papero.minisqlite.domain.mapper

import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse

fun <T, R> List<T?>?.toMapperList(): List<R> {
    val itemList = mutableListOf<R>()
    this?.forEach {
        when (it) {
            is GoogleGithubResponse -> {
                itemList.add(it.toGoogleRepoEntity() as R)
            }
        }
    }

    return itemList
}