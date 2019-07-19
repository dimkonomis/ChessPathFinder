package com.android.chess.pathfinder.di.module

import androidx.lifecycle.ViewModelProvider
import com.android.chess.pathfinder.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}