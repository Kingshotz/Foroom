package com.example.foroom.steps

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
        LoginPage.enterUserName(userName)
    }

    fun enterPassword(password: String) = apply {
        LoginPage.enterPassword(password)
    }

    fun clickLogIn() = apply {
        LoginPage.clickLogIn()
    }

    fun checkUserNameError(errorText: String) = apply {
        waitFor(LoginPage.userNameError, matches(allOf(isDisplayed(), withText(errorText))))
    }

    fun checkPasswordError(errorText: String) = apply {
        waitFor(LoginPage.passwordError, matches(allOf(isDisplayed(), withText(errorText))))
    }

    /** Opens the registration screen and continues the chain with [RegistrationSteps]. */
    fun clickSignUp(): RegistrationSteps {
        LoginPage.clickSignUp()
        return RegistrationSteps
    }
}
