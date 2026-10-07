package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.WAIT_TIMEOUT_SEC
import com.example.foroom.models.User
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.allOf

object LoginSteps {
    fun checkLoginScreenIsDisplayed() = apply {
        onView(LoginPage.userNameInput).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(LoginPage.passwordInput).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(LoginPage.logInButton).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun enterUserName(userName: String) = apply {
        onView(LoginPage.userNameEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(userName)
    }

    fun enterPassword(password: String) = apply {
        onView(LoginPage.passwordEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(password)
    }

    fun clickLogIn() = apply {
        onView(LoginPage.logInButton).tap(WAIT_TIMEOUT_SEC)
    }

    fun logIn(user: User): HomeSteps {
        enterUserName(user.userName)
        enterPassword(user.password)
        clickLogIn()
        return HomeSteps
    }

    fun checkUserNameError(errorText: String) = apply {
        onView(allOf(LoginPage.userNameError, withText(errorText))).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun checkPasswordError(errorText: String) = apply {
        onView(allOf(LoginPage.passwordError, withText(errorText))).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }
}
