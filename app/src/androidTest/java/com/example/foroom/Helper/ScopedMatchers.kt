package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Matches the view with [childId] inside [parent].
 * Child ids of custom views (inputEditText, descriptionTextView, ...) repeat in every
 * instance, so they must always be scoped to their parent.
 */
fun childOf(parent: Matcher<View>, childId: Int): Matcher<View> =
    allOf(withId(childId), isDescendantOfA(parent))
