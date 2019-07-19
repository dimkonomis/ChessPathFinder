package com.android.chess.pathfinder.data.entity

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class Knight : Piece() {

    override val moves: IntArray
        get() = intArrayOf(-1, +2, +1, +2, -2, +1, +2, +1, -2, -1, +2, -1, -1, -2, +1, -2)

    override fun findNeighbors(x: Int, y: Int, size: Int): List<Square> =
        mutableListOf<Square>().apply {
            for (i in 0 until moves.size - 1 step 2) {
                val x1 = x + moves[i]
                val y1 = y + moves[i + 1]
                if (isValidMove(x1, y1, size)) add(Path(x1, y1))
            }
        }

}