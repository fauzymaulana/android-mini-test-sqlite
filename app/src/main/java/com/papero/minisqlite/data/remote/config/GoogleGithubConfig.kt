package com.papero.minisqlite.data.remote.config

import com.papero.minisqlite.core.networking.RequestClient

object GoogleGithubConfig {

    private val reqClient by lazy { RequestClient() }

    fun usersApi(): GoogleGithubService {
        return reqClient.getClient().create(GoogleGithubService::class.java)
    }
}