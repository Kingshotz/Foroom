package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.inputDescription
import com.example.foroom.Helper.inputEditText
import com.example.foroom.Helper.isShownWithText
import com.example.foroom.Helper.waitUntil
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

object LoginPage {
    // Registration reuses userNameInput, passwordInput and signUpButton ids, so they are
    // scoped to the login screen through the Log In button they sit next to.
    private val onLoginScreen = hasSibling(withId(R.id.logInButton))

    val logInButton: Matcher<View> = withId(R.id.logInButton)
    val signUpButton: Matcher<View> = allOf(withId(R.id.signUpButton), onLoginScreen)
    val userNameInput: Matcher<View> = allOf(withId(R.id.userNameInput), onLoginScreen)
    val passwordInput: Matcher<View> = allOf(withId(R.id.passwordInput), onLoginScreen)

    private val userNameEditText = inputEditText(userNameInput)
    private val passwordEditText = inputEditText(passwordInput)
    private val userNameDescription = inputDescription(userNameInput)
    private val passwordDescription = inputDescription(passwordInput)

    fun waitUntilDisplayed() {
        logInButton.waitUntil()
        userNameInput.waitUntil()
        passwordInput.waitUntil()
        signUpButton.waitUntil()
    }

    fun enterUserName(userName: String) {
        userNameEditText.waitUntil().perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        passwordEditText.waitUntil().perform(replaceText(password), closeSoftKeyboard())
    }

    fun tapLogIn() {
        logInButton.waitUntil().perform(click())
    }

    fun tapSignUp() {
        signUpButton.waitUntil().perform(click())
    }

    fun waitForUserNameError() {
        userNameDescription.waitUntil(isShownWithText())
    }

    fun waitForPasswordError() {
        passwordDescription.waitUntil(isShownWithText())
    }

    fun checkUserNameErrorNotDisplayed() {
        onView(userNameDescription).check(matches(not(isDisplayed())))
    }
}
