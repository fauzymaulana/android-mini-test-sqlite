package com.papero.minisqlite.core.state

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papero.minisqlite.core.exceptions.RetrofitException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException

fun <T, R> responseBaseDataSourceApiToResultState(
    baseDataSourceApi: Response<T>?,
    onSuccess: (T) -> R,
): ResultState<R> {
    when (baseDataSourceApi?.code()) {
        200 -> {
            val data = baseDataSourceApi.body()
            return if (data != null) {
                onSuccess(data)
                ResultState.Success(onSuccess(data))
            } else {
                ResultState.UnknownError(
                    "UnknownError" ?: "",
                    null, baseDataSourceApi.code()
                )
            }
        }
        400 -> {
            val errorMessage = baseDataSourceApi.message() ?:
            "Bad Request"

            return ResultState.BadRequest(
                errorMessage,
                null, code = baseDataSourceApi.code()
            )
        }
        401 -> {
            return ResultState.Unauthorized(
                baseDataSourceApi.message() ?: "",
                null, code = baseDataSourceApi.code()
            )
        }
        404 -> {
            val errorMessage = baseDataSourceApi.message() ?:
            "Akses tidak ditemukan"

            return ResultState.NotFound(
                errorMessage,
                null, code = baseDataSourceApi.code()
            )
        }
        403 -> {
            val errorMessage = baseDataSourceApi.message() ?:
            "Akses ditolak"

            return ResultState.Forbidden(
                errorMessage,
                null, code = baseDataSourceApi.code()
            )
        }
        409 -> {
            return ResultState.Conflict(
                baseDataSourceApi.message() ?: "",
                null, code = baseDataSourceApi.code()
            )
        }
        else -> {
            val data = baseDataSourceApi?.body()
            return if (data != null) {
                ResultState.Success(onSuccess(data))
            } else {
                ResultState.UnknownError(
                    baseDataSourceApi?.message() ?: "",
                    null, baseDataSourceApi?.code() ?: 0
                )
            }
        }
    }

}

fun <R> responseErrorToResultStateError(
    error: Throwable,
): ResultState<R> {

    when (error) {
        is HttpException -> {
            when (error.code()) {
                400 -> {
                    return ResultState.BadRequest(
                        message = error.localizedMessage.toString(),
                        code = error.code(),
                        data = null
                    )
                }
                401 -> {
                    return ResultState.Unauthorized(
                        "Sesi akunmu berakhir", null, code = error.code()
                    )

                }
                404, 4041 -> {
                    return ResultState.NotFound(
                        message = error.localizedMessage.toString(),
                        code = error.code(),
                        data = null
                    )
                }
                403, 4032 -> {
                    return ResultState.Forbidden(
                        message = error.localizedMessage.toString(),
                        code = error.code(),
                        data = null
                    )
                }
                409, 406 -> {
                    return ResultState.Conflict(
                        message = error.localizedMessage.toString(),
                        code = error.code(),
                        data = null
                    )
                }
                else -> {
                    return if (error.code() >= 500) {
                        ResultState.UnknownError(
                            "Maaf, Terjadi gangguan pada server [${error.code()}]",
                            null, code = error.code()
                        )
                    } else {
                        return ResultState.UnknownError(
                            message = error.localizedMessage.toString(),
                            code = error.code(),
                            data = null
                        )
                    }

                }
            }
        }
        is RetrofitException -> {
            return when (error.code) {
                1 -> {
                    ResultState.NoConnection(
                        "Tidak ada koneksi internet", null, error.code
                    )
                }
                2 -> {
                    ResultState.UnknownError(
                        "Terjadi gangguan pada aplikasi [00${error.code}]", null,
                        error.code
                    )
                }
                else -> {
                    ResultState.UnknownError(
                        "Terjadi gangguan pada aplikasi [00${error.code}]", null,
                        error.code ?: 0
                    )
                }
            }

        }
        is IOException -> {
            return ResultState.NoConnection(
                "Jaringan kamu sedang tidak stabil", null, 0
            )
        }
        is TimeoutException -> {
            return ResultState.Timeout(
                "Waktu Akses Habis", null, 0
            )
        }
        else -> {
            return ResultState.UnknownError(
                error.message ?: "Maaf, Terjadi gangguan pada server", null, 0
            )
        }
    }

}

fun <T, R> responseMapsToResultState(
    routeEntity: T,
    onSuccess: (T) -> R,
): ResultState<R> {
    onSuccess(routeEntity)
    return ResultState.Success(onSuccess(routeEntity))
}