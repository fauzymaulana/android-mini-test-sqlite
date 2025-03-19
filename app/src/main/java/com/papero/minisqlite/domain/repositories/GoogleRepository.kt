package com.papero.minisqlite.domain.repositories

import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result

interface GoogleRepository {
    fun fetchRepositories(): Single<Result<List<GoogleGithubResponse>>>
}