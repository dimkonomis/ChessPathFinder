package com.android.chess.pathfinder.data.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Singleton
class SchedulerProviderImpl : SchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun multi(): Scheduler {
        return Schedulers.newThread()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}