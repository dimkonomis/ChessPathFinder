package com.android.chess.pathfinder.di.module

import androidx.lifecycle.ViewModel
import com.android.chess.pathfinder.di.ViewModelKey
import com.android.chess.pathfinder.presentation.ui.board.BoardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Module
abstract class BoardModule {

    @Binds
    @IntoMap
    @ViewModelKey(BoardViewModel::class)
    abstract fun bindBoardViewModel(viewModel: BoardViewModel): ViewModel

}