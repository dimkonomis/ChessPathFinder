package com.android.chess.pathfinder.domain.usecase

import com.android.chess.pathfinder.data.entity.Knight
import com.android.chess.pathfinder.data.entity.Path
import com.android.chess.pathfinder.data.entity.Piece
import com.android.chess.pathfinder.data.executor.SchedulerProvider
import com.android.chess.pathfinder.data.repository.BoardRepository
import com.android.chess.pathfinder.domain.exception.PathNotFoundException
import com.android.chess.pathfinder.domain.mapper.toPointsList
import com.android.chess.pathfinder.domain.model.Pawn
import com.android.chess.pathfinder.domain.model.Point
import io.reactivex.Single
import javax.inject.Singleton

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

@Singleton
class BoardUseCase(
    private val repository: BoardRepository,
    private val schedulerProvider: SchedulerProvider
) {

    val boardSize: Int
        get() = 8

    val maxMoves: Int
        get() = 3

    fun startPathFinding(startX: Int, startY: Int, endX: Int, endY: Int, pawn: Pawn): Single<List<List<Point>>> {
        return repository.findPaths(Path(startX, startY), Path(endX, endY), boardSize, maxMoves, selectPiece(pawn))
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .flattenAsObservable { paths -> if(paths.isNotEmpty()) paths else throw PathNotFoundException() }
            .map { pathList -> pathList.toPointsList() }
            .toList()
    }

    private fun selectPiece(pawn: Pawn): Piece =
        when(pawn) {
            Pawn.Knight -> Knight()
        }

}