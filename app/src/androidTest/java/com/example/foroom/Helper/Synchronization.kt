package com.example.foroom.Helper

import android.os.SystemClock
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

const val DEFAULT_TIMEOUT_MS = 10_000L
private const val POLL_INTERVAL_MS = 100L

/**
 * Polls until the view matched by [this] satisfies [condition], then returns its [ViewInteraction].
 * Network requests run on coroutines that Espresso does not track, so a plain check could run too early.
 */
fun Matcher<View>.waitUntil(
    condition: Matcher<View> = isDisplayed(),
    timeoutMs: Long = DEFAULT_TIMEOUT_MS
): ViewInteraction {
    val interaction = onView(this)
    pollUntil(timeoutMs) { interaction.check(ViewAssertions.matches(condition)) }
    return interaction
}

/**
 * Polls until no view matched by [this] is left in the hierarchy.
 */
fun Matcher<View>.waitUntilGone(timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
    pollUntil(timeoutMs) { onView(this).check(doesNotExist()) }
}

private fun pollUntil(timeoutMs: Long, assertion: () -> Unit) {
    val endTime = SystemClock.uptimeMillis() + timeoutMs
    while (true) {
        try {
            assertion()
            return
        } catch (error: Throwable) {
            if (SystemClock.uptimeMillis() >= endTime) throw error
            SystemClock.sleep(POLL_INTERVAL_MS)
        }
    }
}
