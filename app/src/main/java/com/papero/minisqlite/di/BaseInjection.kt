package com.papero.minisqlite.di

import com.papero.minisqlite.data.data_source.GoogleRepositoriesDataSourceImpl
import com.papero.minisqlite.data.remote.config.GoogleGithubConfig
import com.papero.minisqlite.data.repositories.GoogleRepositoryImpl
import com.papero.minisqlite.domain.usecases.FetchGoogleRepoUseCaseImpl

class BaseInjection {
    private val configApi by lazy { GoogleGithubConfig.usersApi() }
    private val googleReposDataSource by lazy { GoogleRepositoriesDataSourceImpl(configApi) }
    private val googleReposRepository by lazy { GoogleRepositoryImpl(googleReposDataSource) }

    val fetchGoogleReposUsecase by lazy { FetchGoogleRepoUseCaseImpl(googleReposRepository) }

}