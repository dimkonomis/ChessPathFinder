package com.android.chess.pathfinder.data.repository

import com.android.chess.pathfinder.data.entity.Path
import com.android.chess.pathfinder.data.entity.Piece
import com.android.chess.pathfinder.data.entity.Square
import io.reactivex.Single

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

interface BoardRepository {

    fun findPaths(start: Square, end: Square, size: Int, maxMoves: Int, piece: Piece): Single<List<List<Square>>>

}