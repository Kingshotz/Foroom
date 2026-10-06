package com.example.foroom.steps

import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.click
import com.example.foroom.Helper.enterText
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.allOf

/** Steps of the login screen. Every step returns the next steps object, so calls can be chained. */
object LoginSteps {

    fun checkLoginScreenIsDisplayed() = apply {
        LoginPage.userNameInput.waitUntilDisplayed()
        LoginPage.passwordInput.waitUntilDisplayed()
        LoginPage.logInButton.waitUntilDisplayed()
    }

    fun enterUserName(userName: String) = apply {
        LoginPage.userNameEditText.enterText(userName)
    }

    fun enterPassword(password: String) = apply {
        LoginPage.passwordEditText.enterText(password)
    }

    fun clickLogIn() = apply {
        LoginPage.logInButton.click()
    }

    /** Fills in the credentials, logs in and continues on the home screen. */
    fun logIn(userName: String, password: String): HomeSteps {
        enterUserName(userName)
        enterPassword(password)
        clickLogIn()
        return HomeSteps
    }

    fun checkUserNameError(errorText: String) = apply {
        allOf(LoginPage.userNameError, withText(errorText)).waitUntilDisplayed()
    }

    fun checkPasswordError(errorText: String) = apply {
        allOf(LoginPage.passwordError, withText(errorText)).waitUntilDisplayed()
    }
}
