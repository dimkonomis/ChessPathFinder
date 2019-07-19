package com.android.chess.pathfinder.data.entity

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

abstract class Piece {

    abstract val moves: IntArray

    abstract fun findNeighbors(x: Int, y: Int, size: Int): List<Square>

    fun isValidMove(x: Int, y: Int, size: Int): Boolean =
        x >= 0 && y >= 0 && x < size && y < size

}