package com.android.chess.pathfinder.presentation.common

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

sealed class State<T> {

    data class Loading<T>(var data: T?): State<T>()
    data class Error<T>(val errorMessage: String?, val error: Throwable): State<T>()
    data class Success<T>(var data: T) : State<T>()

    companion object {
        fun <T> loading(data: T? = null): State<T> =
            Loading(data)

        fun <T> error(errorMessage: String?, error: Throwable): State<T> =
            Error(errorMessage, error)

        fun <T> success(data: T): State<T> =
            Success(data)
    }

}