package com.android.chess.pathfinder.data.repository

import com.android.chess.pathfinder.data.entity.Piece
import com.android.chess.pathfinder.data.entity.Square
import com.android.chess.pathfinder.data.source.Chess
import io.reactivex.Single

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class BoardRepositoryImpl(private val chess: Chess) : BoardRepository {

    override fun findPaths(start: Square, end: Square, size: Int, maxMoves: Int, piece: Piece): Single<List<List<Square>>> {
        return Single.just(
            chess.startTour(start, end, size, maxMoves, piece)
        )
    }

}