package com.papero.minisqlite.data.data_source

import com.papero.minisqlite.data.remote.config.GoogleGithubService
import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result

interface GoogleRepositoriesDataSource {
    fun fetchRepos(): Single<Result<List<GoogleGithubResponse>>>
}

class GoogleRepositoriesDataSourceImpl(
    private val api: GoogleGithubService
): GoogleRepositoriesDataSource {
    override fun fetchRepos(): Single<Result<List<GoogleGithubResponse>>> {
        return api.fetchUsers()
    }
}