package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.waitFor
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.allOf

/** Every step returns the steps object, so calls can be chained. */
object LoginSteps {

    fun checkLoginScreenIsDisplayed() = apply {
        waitFor(LoginPage.userNameInput)
        waitFor(LoginPage.passwordInput)
        waitFor(LoginPage.logInButton)
    }

    fun enterUserName(userName: String) = apply {
        onView(LoginPage.userNameEditText).perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) = apply {
        onView(LoginPage.passwordEditText).perform(replaceText(password), closeSoftKeyboard())
    }

    fun clickLogIn() = apply {
        onView(LoginPage.logInButton).perform(click())
    }

    fun checkUserNameError(errorText: String) = apply {
        waitFor(LoginPage.userNameError, matches(allOf(isDisplayed(), withText(errorText))))
    }

    fun checkPasswordError(errorText: String) = apply {
        waitFor(LoginPage.passwordError, matches(allOf(isDisplayed(), withText(errorText))))
    }

    /** Opens the registration screen and continues the chain with [RegistrationSteps]. */
    fun clickSignUp(): RegistrationSteps {
        onView(LoginPage.signUpButton).perform(click())
        return RegistrationSteps
    }
}
