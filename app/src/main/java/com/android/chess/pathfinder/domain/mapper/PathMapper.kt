package com.android.chess.pathfinder.domain.mapper

import com.android.chess.pathfinder.data.entity.Path
import com.android.chess.pathfinder.data.entity.Square
import com.android.chess.pathfinder.domain.model.Point

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

fun List<Square>.toPointsList(): List<Point> =
    map { Point(it.x, it.y) }