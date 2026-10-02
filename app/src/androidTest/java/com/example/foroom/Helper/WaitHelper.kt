package com.example.foroom.Helper

import android.os.SystemClock
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

/**
 * Repeats the check until it passes or the timeout runs out.
 * Login, registration and avatar loading run in the background, so Espresso does not wait for them.
 */
fun waitFor(
    view: Matcher<View>,
    assertion: ViewAssertion = matches(isDisplayed()),
    timeoutMs: Long = 10_000
) {
    val endTime = SystemClock.uptimeMillis() + timeoutMs
    while (true) {
        try {
            onView(view).check(assertion)
            return
        } catch (error: Throwable) {
            if (SystemClock.uptimeMillis() > endTime) throw error
            SystemClock.sleep(100)
        }
    }
}
