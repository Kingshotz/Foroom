package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.design_system.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

/**
 * Children of the custom `Input` view share the same ids in every input,
 * so they are always scoped to their parent input.
 */
fun inputEditText(input: Matcher<View>): Matcher<View> =
    allOf(withId(R.id.inputEditText), isDescendantOfA(input))

fun inputDescription(input: Matcher<View>): Matcher<View> =
    allOf(withId(R.id.descriptionTextView), isDescendantOfA(input))

/** A description is shown only when the input has a message, e.g. an error returned by the server. */
fun isShownWithText(): Matcher<View> = allOf(isDisplayed(), not(withText("")))
