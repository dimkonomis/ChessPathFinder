package com.android.chess.pathfinder.presentation.common

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Activity.showSnackBarMessage(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, length).show()
}

fun View.visible(visible: Boolean, invisibility: Int = View.GONE) {
    visibility = if (visible) View.VISIBLE else invisibility
}

fun View.backgroundColor(colorRes: Int) {
    setBackgroundColor(context.color(colorRes))
}

fun View.center(): IntArray {
    val position = IntArray(2)
    getLocationOnScreen(position)

    // moving pivot to center
    position[0] += getMeasuredWidth() / 2
    position[1] += getMeasuredHeight() / 2

    // taking status bar top offset into account
    position[1] -= context.getStatusBarHeight()

    return position
}