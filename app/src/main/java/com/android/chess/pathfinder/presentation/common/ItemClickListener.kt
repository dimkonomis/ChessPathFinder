package com.android.chess.pathfinder.presentation.common

import android.view.View

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

interface ItemClickListener<T> {
    fun onItemClick(v: View, position: Int, item: T)
}