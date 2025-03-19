package com.papero.minisqlite.data.remote.config

import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.adapter.rxjava2.Result

interface GoogleGithubService {

    @GET(Routes.GOOGLE_REPOS)
    fun fetchUsers(): Single<Result<List<GoogleGithubResponse>>>
}