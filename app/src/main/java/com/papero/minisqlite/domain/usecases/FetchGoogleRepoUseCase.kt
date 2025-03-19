package com.papero.minisqlite.domain.usecases

import com.papero.minisqlite.core.state.ResultState
import com.papero.minisqlite.core.state.responseBaseDataSourceApiToResultState
import com.papero.minisqlite.core.state.responseErrorToResultStateError
import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse
import com.papero.minisqlite.domain.entities.GoogleRepoEntity
import com.papero.minisqlite.domain.mapper.toMapperList
import com.papero.minisqlite.domain.repositories.GoogleRepository
import io.reactivex.Single

interface FetchGoogleRepoUseCase {
    operator fun invoke(): Single<ResultState<List<GoogleRepoEntity>>>
}

class FetchGoogleRepoUseCaseImpl(
    private val repo: GoogleRepository
): FetchGoogleRepoUseCase {
    override fun invoke(): Single<ResultState<List<GoogleRepoEntity>>> {
        return repo.fetchRepositories()
            .map { response ->
                return@map responseBaseDataSourceApiToResultState(response.response()) {
                    it.toMapperList<GoogleGithubResponse, GoogleRepoEntity>()
                }
            }
            .onErrorReturn { err ->
                return@onErrorReturn responseErrorToResultStateError(err)
            }
    }
}