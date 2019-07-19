package com.android.chess.pathfinder.presentation.ui.board

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.chess.pathfinder.R
import com.android.chess.pathfinder.domain.exception.PathNotFoundException
import com.android.chess.pathfinder.domain.model.Point
import com.android.chess.pathfinder.presentation.common.*
import com.android.chess.pathfinder.presentation.ui.base.BaseActivityDagger
import kotlinx.android.synthetic.main.activity_board.*
import javax.inject.Inject

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class BoardActivity: BaseActivityDagger() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BoardViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(BoardViewModel::class.java)
    }

    override fun getLayout(): Int = R.layout.activity_board

    private val itemClickListener = object: ItemClickListener<Pair<Int, Int>> {
        override fun onItemClick(v: View, position: Int, item: Pair<Int, Int>) {
            // first is the squares's row and second is the squares's column
            viewModel.selectPosition(item.first, item.second)
        }
    }
    private val adapter = BoardAdapter(arrayListOf(), itemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        chessRecycler.layoutParams.height = resources.displayMetrics.heightPixels / 2
        chessRecycler.layoutManager = GridLayoutManager(this, viewModel.size, RecyclerView.VERTICAL, true)
        chessRecycler.setHasFixedSize(true)
        chessRecycler.adapter = adapter

        startButton.setOnClickListener { viewModel.startFinding() }
        clearButton.setOnClickListener { viewModel.reset() }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.squares.observe(this, Observer<Array<Array<Boolean>>> { squares ->
            adapter.update(squares)
        })
        viewModel.paths.observe(this, Observer<State<List<List<Point>>>> { state ->
            dismissLoading()
            when(state) {
                is State.Loading -> showLoading()
                is State.Success -> {
                    showClearButton()
                    drawPoints(state.data)
                }
                is State.Error -> {
                    showClearButton()
                    showError(state.error)
                }
            }
        })
        viewModel.isValidToStartFinding.observe(this, Observer<Boolean> { isValid ->
            chessRecycler.disableItemTouch(isValid)
            startButton.visible(isValid)
            clearButton.visible(isValid)
        })
    }

    private fun showClearButton() {
        startButton.visible(false)
        clearButton.visible(true)
    }

    private fun drawPoints(pointList: List<List<Point>>) {
        pathsView.clearPoints()
        pointList.forEach { points ->
            val list = mutableListOf<Point>()
            points.forEach { point ->
                val view: View = chessRecycler.getChildAt(point.x * viewModel.size + point.y)
                val viewCenter: IntArray = view.center()
                list.add(Point(viewCenter[0], viewCenter[1]))
            }
            pathsView.addPoints(list)
        }
        pathsView.invalidate()
    }

    private fun showError(throwable: Throwable) {
        showSnackBarMessage(root, when(throwable) {
            is PathNotFoundException -> getString(R.string.no_path)
            else -> throwable.message ?: "Error"
        })
    }
}