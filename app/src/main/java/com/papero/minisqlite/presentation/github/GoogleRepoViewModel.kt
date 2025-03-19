package com.papero.minisqlite.presentation.github


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.papero.minisqlite.CoreApplication
import com.papero.minisqlite.core.base.BaseViewModel
import com.papero.minisqlite.core.state.ResultState
import com.papero.minisqlite.domain.entities.GoogleRepoEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GoogleRepoViewModel : BaseViewModel() {
    private val coreInjection by lazy { CoreApplication().coreInjection }
    private val fetchRepoGithubUsecase by lazy { coreInjection.fetchGoogleReposUsecase }

    private val _allRepoGoogle by lazy { MutableLiveData<ResultState<List<GoogleRepoEntity>>>() }
    val allRepoGoogle: LiveData<ResultState<List<GoogleRepoEntity>>> = _allRepoGoogle

    fun fetchAllRepoGoogle() {
        val disposable = fetchRepoGithubUsecase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { resultState ->
                    _allRepoGoogle.value = resultState
                },
                { e ->
                    _allRepoGoogle.value = ResultState.UnknownError(
                        message = e.message.toString(),
                        code = 0,
                        data = null
                    )
                }
            )

        addDisposable(disposable)
    }
}