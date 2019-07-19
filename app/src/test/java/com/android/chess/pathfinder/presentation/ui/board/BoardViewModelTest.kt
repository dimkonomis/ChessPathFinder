package com.android.chess.pathfinder.presentation.ui.board

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.chess.pathfinder.domain.exception.PathNotFoundException
import com.android.chess.pathfinder.domain.model.Pawn
import com.android.chess.pathfinder.domain.model.Point
import com.android.chess.pathfinder.domain.usecase.BoardUseCase
import com.android.chess.pathfinder.presentation.common.State
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 */

@RunWith(MockitoJUnitRunner::class)
class BoardViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    val boardUseCase = mock<BoardUseCase>()

    private lateinit var viewModel: BoardViewModel
    private val startX = 0
    private val startY = 0
    private val endX = 1
    private val endY = 2

    @Before
    fun setup() {
        viewModel = BoardViewModel(boardUseCase)
    }

    @Test
    fun testStartFindingThenStateSuccess() {
        val response: List<List<Point>> = listOf(listOf(Point(0, 0), Point(1, 2)))
        val observer: Observer<State<List<List<Point>>>> = mock()
        val pawn = Pawn.Knight

        whenever(boardUseCase.startPathFinding(startX, startY, endX, endY, pawn)).doReturn(Single.just(response))

        selectPositions()
        viewModel.paths.observeForever(observer)
        viewModel.startFinding()

        verify(boardUseCase).startPathFinding(startX, startY, endX, endY, pawn)
        verify(observer).onChanged(State.loading())
        verify(observer).onChanged(State.success(response))
    }

    @Test
    fun testStartFindingThenStateError() {
        val error = PathNotFoundException()
        val observer: Observer<State<List<List<Point>>>> = mock()
        val pawn = Pawn.Knight

        whenever(boardUseCase.startPathFinding(startX, startY, endX, endY, pawn)).doReturn(Single.error(error))

        selectPositions()
        viewModel.paths.observeForever(observer)
        viewModel.startFinding()

        verify(boardUseCase).startPathFinding(startX, startY, endX, endY, pawn)
        verify(observer).onChanged(State.loading())
        verify(observer).onChanged(State.error(error.message, error))
    }

    @Test
    fun testSelectPositionThenValidToStartTrue() {
        val observer: Observer<Boolean> = mock()

        selectPositions()
        viewModel.isValidToStartFinding.observeForever(observer)

        verify(observer).onChanged(true)
    }

    @Test
    fun testResetThenStateEmptyListAndValidToStartFalse() {
        val points: List<List<Point>> = emptyList()
        val isValid = false
        val observerPath: Observer<State<List<List<Point>>>> = mock()
        val observerValid: Observer<Boolean> = mock()

        viewModel.paths.observeForever(observerPath)
        viewModel.isValidToStartFinding.observeForever(observerValid)
        viewModel.reset()

        verify(observerPath).onChanged(State.success(points))
        verify(observerValid).onChanged(isValid)
    }

    private fun selectPositions() {
        viewModel.selectPosition(startX, startY)
        viewModel.selectPosition(endX, endY)
    }
}