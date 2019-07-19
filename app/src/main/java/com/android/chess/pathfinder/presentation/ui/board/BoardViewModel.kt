package com.android.chess.pathfinder.presentation.ui.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.chess.pathfinder.domain.model.Pawn
import com.android.chess.pathfinder.domain.model.Point
import com.android.chess.pathfinder.domain.usecase.BoardUseCase
import com.android.chess.pathfinder.presentation.common.State
import com.android.chess.pathfinder.presentation.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

class BoardViewModel @Inject constructor(private val boardUseCase: BoardUseCase) : BaseViewModel() {

    val size = boardUseCase.boardSize

    private val pawn = Pawn.Knight
    private val selectedPoints: LinkedHashSet<Point> = linkedSetOf()
    private val _isValidToStartFinding: MutableLiveData<Boolean> = MutableLiveData()
    private val _paths: MutableLiveData<State<List<List<Point>>>> = MutableLiveData()
    private val _squares = MutableLiveData<Array<Array<Boolean>>>().apply {
        value = Array(size) { Array(size) { false } } // Initialize 2D size*size array
    }

    val squares: LiveData<Array<Array<Boolean>>>
        get() = _squares

    val paths: LiveData<State<List<List<Point>>>>
        get() = _paths

    val isValidToStartFinding: LiveData<Boolean>
        get() = _isValidToStartFinding

    fun selectPosition(squareRow: Int, squareCol: Int) {
        selectedPoints.add(Point(x = squareRow, y = squareCol))
        _isValidToStartFinding.postValue(selectedPoints.size == 2)
        postSquares()
    }

    fun startFinding() {
        val start = selectedPoints.first()
        val end = selectedPoints.last()
        boardUseCase.startPathFinding(start.x, start.y, end.x, end.y, pawn)
            .doOnSubscribe { _paths.postValue(State.loading()) }
            .subscribeBy(
                onSuccess = { _paths.postValue(State.success(it)) },
                onError = { e -> _paths.postValue(State.error(e.message, e)) }
            )
            .addTo(compositeDisposable)
    }

    fun reset() {
        selectedPoints.clear()
        _paths.postValue(State.success(listOf()))
        _isValidToStartFinding.postValue(false)
        postSquares()
    }

    private fun postSquares() {
        _squares.postValue(Array(size) { row ->
            Array(size) { col ->
                selectedPoints.find { item -> item.x == row && item.y == col } != null
            }
        })
    }
}