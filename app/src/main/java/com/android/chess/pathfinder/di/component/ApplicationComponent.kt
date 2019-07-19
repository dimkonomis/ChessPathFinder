package com.android.chess.pathfinder.di.component

import android.app.Application
import com.android.chess.pathfinder.ChessApplication
import com.android.chess.pathfinder.di.builder.BoardBuilder
import com.android.chess.pathfinder.di.module.ApplicationModule
import com.android.chess.pathfinder.di.module.RepositoryModule
import com.android.chess.pathfinder.di.module.UseCaseModule
import com.android.chess.pathfinder.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    ViewModelModule::class,
    BoardBuilder::class
])
interface ApplicationComponent : AndroidInjector<ChessApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun useCaseModule(useCaseModule: UseCaseModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(application: ChessApplication)
}