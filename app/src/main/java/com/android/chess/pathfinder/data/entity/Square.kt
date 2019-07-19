package com.android.chess.pathfinder.data.entity

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

interface Square {
    val x: Int
    val y: Int
}

data class Path(
    override val x: Int,
    override val y: Int
) : Square