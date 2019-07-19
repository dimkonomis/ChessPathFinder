package com.android.chess.pathfinder.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class TouchRecyclerView(context: Context, attrs: AttributeSet?): RecyclerView(context, attrs) {

    private val disableTouchListener = object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            // true: consume touch event
            // false: dispatch touch event
            return true
        }
    }

    fun disableItemTouch(disable: Boolean) {
        if(disable) addOnItemTouchListener(disableTouchListener) else removeOnItemTouchListener(disableTouchListener)
    }

}