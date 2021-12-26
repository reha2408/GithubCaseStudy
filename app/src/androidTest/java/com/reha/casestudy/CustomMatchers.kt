package com.reha.casestudy

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withListSize(size: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        public override fun matchesSafely(view: View): Boolean {
            return (view as RecyclerView).adapter?.itemCount == size
        }
        override fun describeTo(description: Description) {
            description.appendText("ListView should have $size items")
        }
    }
}
