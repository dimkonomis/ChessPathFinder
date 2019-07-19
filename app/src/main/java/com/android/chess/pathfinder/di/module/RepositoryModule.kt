package com.android.chess.pathfinder.di.module

import com.android.chess.pathfinder.data.repository.BoardRepository
import com.android.chess.pathfinder.data.repository.BoardRepositoryImpl
import com.android.chess.pathfinder.data.source.Chess
import dagger.Module
import dagger.Provides

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Module
class RepositoryModule {

    @Provides
    fun provideChess(): Chess {
        return Chess()
    }

    @Provides
    fun provideBoardRepository(chess: Chess): BoardRepository {
        return BoardRepositoryImpl(chess)
    }

}