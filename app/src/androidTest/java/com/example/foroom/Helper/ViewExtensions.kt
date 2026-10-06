package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import com.example.foroom.data.Constants
import org.hamcrest.Matcher

/**
 * Short, reusable wrappers over the project's waiting helpers (waitUntilVisible, tap, input).
 * They wait for background work (login, language change, chat loading) before acting.
 */

/** Waits until a view matching [this] is displayed. */
fun Matcher<View>.waitUntilDisplayed(timeoutSec: Long = Constants.WAIT_TIMEOUT_SEC): ViewInteraction =
    onView(this).waitUntilVisible(timeoutSec)

/** Waits until a view matching [this] is displayed, then clicks it. */
fun Matcher<View>.click(timeoutSec: Long = Constants.WAIT_TIMEOUT_SEC) {
    waitUntilDisplayed(timeoutSec).tap()
}

/** Waits until a view matching [this] is displayed, then replaces its text. */
fun Matcher<View>.enterText(text: String, timeoutSec: Long = Constants.WAIT_TIMEOUT_SEC) {
    waitUntilDisplayed(timeoutSec).input(text)
}
