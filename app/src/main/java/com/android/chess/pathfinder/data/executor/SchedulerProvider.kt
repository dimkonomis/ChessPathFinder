package com.android.chess.pathfinder.data.executor

import io.reactivex.Scheduler

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

interface SchedulerProvider {

    fun computation(): Scheduler

    fun multi(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
