package com.android.chess.pathfinder.di.module

import android.content.Context
import com.android.chess.pathfinder.data.executor.SchedulerProvider
import com.android.chess.pathfinder.data.executor.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

}