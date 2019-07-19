package com.android.chess.pathfinder.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.android.chess.pathfinder.domain.model.Point
import java.util.*

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class PathsView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val linesPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pointsList: MutableList<List<Point>> = mutableListOf()
    private val rnd = Random()

    init {
        linesPaint.style = Paint.Style.STROKE
        linesPaint.strokeWidth = 8f
    }

    fun clearPoints() {
        pointsList.clear()
    }

    fun addPoints(points: List<Point>) {
        pointsList.add(points)
    }

    override fun onDraw(canvas: Canvas) {
        for (points in pointsList) {
            setPaintColor()
            for (index in 0 until points.size - 1) {
                val from = points[index]
                val to = points[index + 1]
                canvas.drawLine(from.x.toFloat(), from.y.toFloat(), to.x.toFloat(), to.y.toFloat(), linesPaint)
                canvas.drawCircle(to.x.toFloat(), to.y.toFloat(), 10f, linesPaint)
            }
        }
    }

    private fun setPaintColor() {
        linesPaint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}