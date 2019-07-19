package com.android.chess.pathfinder.di.builder

import com.android.chess.pathfinder.di.module.BoardModule
import com.android.chess.pathfinder.presentation.ui.board.BoardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Module
interface BoardBuilder {

    @ContributesAndroidInjector(modules = [BoardModule::class])
    fun contributeBoardActivity(): BoardActivity
}