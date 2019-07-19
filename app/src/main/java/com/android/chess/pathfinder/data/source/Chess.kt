package com.android.chess.pathfinder.data.source

import com.android.chess.pathfinder.data.entity.Path
import com.android.chess.pathfinder.data.entity.Piece
import com.android.chess.pathfinder.data.entity.Square

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-19.
 * Based on https://www.techiedelight.com/print-possible-knights-tours-chessboard/
 **/

class Chess {

    fun startTour(start: Square, end: Square, size: Int, maxMoves: Int, piece: Piece): MutableList<List<Square>> {
        val reachedPaths: MutableList<List<Square>> = mutableListOf()
        val currentPath: MutableList<Square> = mutableListOf()
        findPath(start.x, start.y, end.x, end.y, size, maxMoves, piece, reachedPaths, currentPath)
        return reachedPaths
    }

    private fun findPath(startX: Int, startY: Int, endX: Int, endY: Int, size: Int, remaining: Int,
                              piece: Piece, reachedPaths: MutableList<List<Square>>, currentPath: MutableList<Square>) {

        if(remaining < 0) return

        // mark current square as visited
        val path = Path(startX, startY)
        currentPath.add(path)

        // if reached end, return path
        if (startX == endX && startY == endY) {
            reachedPaths.add(currentPath.toList())
            // backtrack before returning
            currentPath.remove(path)
            return
        }

        // check for all possible movements for a piece
        // and recur for each valid movement
        for (k in 0 until piece.moves.size - 1 step 2) {
            // Get the new position from current position on chessboard
            val newX = startX + piece.moves[k]
            val newY = startY + piece.moves[k+1]

            // if new position is a valid and not visited yet
            if (piece.isValidMove(newX, newY, size) && !currentPath.contains(Path(newX, newY)))
                findPath(newX, newY, endX, endY, size, remaining - 1, piece, reachedPaths, currentPath)
        }

        // backtrack from current square and remove it from current path
        currentPath.remove(path)
    }
}