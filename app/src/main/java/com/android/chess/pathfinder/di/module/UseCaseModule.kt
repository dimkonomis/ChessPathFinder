package com.android.chess.pathfinder.di.module

import com.android.chess.pathfinder.data.executor.SchedulerProvider
import com.android.chess.pathfinder.data.repository.BoardRepository
import com.android.chess.pathfinder.domain.usecase.BoardUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideBoardUseCase(boardRepository: BoardRepository, schedulerProvider: SchedulerProvider): BoardUseCase {
        return BoardUseCase(boardRepository, schedulerProvider)
    }

}