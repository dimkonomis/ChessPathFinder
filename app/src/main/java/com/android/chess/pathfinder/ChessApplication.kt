package com.android.chess.pathfinder

import com.android.chess.pathfinder.di.component.DaggerApplicationComponent
import com.android.chess.pathfinder.di.module.ApplicationModule
import com.android.chess.pathfinder.di.module.RepositoryModule
import com.android.chess.pathfinder.di.module.UseCaseModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class ChessApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(this))
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
    }

}