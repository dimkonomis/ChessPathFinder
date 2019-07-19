package com.android.chess.pathfinder.presentation

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}

fun withBackgroundColor(@ColorInt color: Int): Matcher<View> {
    return object : BoundedMatcher<View, View>(View::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("View background color to be $color")
        }

        override fun matchesSafely(item: View): Boolean {
            val backgroundColor = (item.background as ColorDrawable).color
            return ContextCompat.getColor(item.context, color) == backgroundColor
        }

    }
}