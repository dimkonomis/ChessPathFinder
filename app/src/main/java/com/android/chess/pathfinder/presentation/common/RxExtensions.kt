package com.android.chess.pathfinder.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import timber.log.Timber

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T> Flowable<T>.toState(): Flowable<State<T>> {
    return compose { item ->
        item
            .map { State.success(it) }
            .startWith(State.loading())
            .onErrorReturn { e -> Timber.e(e); State.error(e.message ?: "Unknown Error", e) }
    }
}

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }