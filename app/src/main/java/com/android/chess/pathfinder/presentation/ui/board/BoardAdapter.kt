package com.android.chess.pathfinder.presentation.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.chess.pathfinder.R
import com.android.chess.pathfinder.presentation.common.ItemClickListener
import com.android.chess.pathfinder.presentation.common.backgroundColor
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_board.*

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class BoardAdapter(
    private val squares: ArrayList<Array<Boolean>>,
    private val itemClickListener: ItemClickListener<Pair<Int, Int>>
): RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    private val rows
        get() = squares.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BoardViewHolder(inflater.inflate(R.layout.item_board, parent, false))
    }

    override fun getItemCount(): Int = rows*rows

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(position)
    }

    fun update(items: Array<Array<Boolean>>) {
        squares.clear()
        squares.addAll(items)
        notifyDataSetChanged()
    }

    inner class BoardViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(position: Int) {
            val row = position / rows
            val col = position % rows
            val color = when{
                squares[row][col] -> R.color.gold
                (row + col) % 2 == 0 -> R.color.black
                else -> R.color.white
            }
            boardView.backgroundColor(color)
            boardView.setOnClickListener { itemClickListener.onItemClick(boardView, position, Pair(row, col)) }
        }
    }

}