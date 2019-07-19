package com.android.chess.pathfinder.presentation.ui.board

import android.os.Build
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.chess.pathfinder.R
import com.android.chess.pathfinder.presentation.withBackgroundColor
import com.android.chess.pathfinder.presentation.withRecyclerView
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 */

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@LooperMode(LooperMode.Mode.PAUSED)
class BoardActivityTest {

    @Test
    fun testClickRecyclerViewItem() {
        val position = 0
        val scenario = createScenario()
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.chessRecycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))

        onView(withRecyclerView(R.id.chessRecycler)
            .atPositionOnView(position, R.id.boardView))
            .check(matches(withBackgroundColor(R.color.gold)))

    }

    @Test
    fun testClickRecyclerViewItemsThenButtonsVisible() {
        val scenario = createScenario()
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.chessRecycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.chessRecycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        onView(withId(R.id.startButton)).check(matches(isDisplayed()))
        onView(withId(R.id.clearButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickRecyclerViewItemsAndClearButtonThenButtonsNotVisible() {
        val scenario = createScenario()
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.chessRecycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.chessRecycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        onView(withId(R.id.clearButton)).perform(click());

        onView(withId(R.id.startButton)).check(matches(not(isDisplayed())))
        onView(withId(R.id.clearButton)).check(matches(not(isDisplayed())))
    }

    private fun createScenario(): ActivityScenario<BoardActivity> {
        return ActivityScenario.launch(BoardActivity::class.java)
    }

}