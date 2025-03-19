package com.papero.minisqlite.data.repositories

import com.papero.minisqlite.core.state.ResultState
import com.papero.minisqlite.core.state.responseBaseDataSourceApiToResultState
import com.papero.minisqlite.core.state.responseErrorToResultStateError
import com.papero.minisqlite.data.data_source.GoogleRepositoriesDataSource
import com.papero.minisqlite.data.remote.entities.GoogleGithubResponse
import com.papero.minisqlite.domain.entities.GoogleRepoEntity
import com.papero.minisqlite.domain.mapper.toMapperList
import com.papero.minisqlite.domain.repositories.GoogleRepository
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result

class GoogleRepositoryImpl(
    private val datasourceApi: GoogleRepositoriesDataSource
): GoogleRepository {
    override fun fetchRepositories(): Single<Result<List<GoogleGithubResponse>>> {
        return datasourceApi.fetchRepos()
//        return try {
//            datasourceApi.fetchRepos()
//                .map { response ->
//                    responseBaseDataSourceApiToResultState(
//                        sourceApi = response,
//                        statusCode = response.
//                    ) {
//                        it.toMapperList<GoogleGithubResponse, GoogleRepoEntity>()
//                    }
//                }
//                .onErrorReturn { err ->
//                    responseErrorToResultStateError(err)
//                }
//        } catch (e: Exception) {
//            Single.just(responseErrorToResultStateError(e))
//        }
    }

}