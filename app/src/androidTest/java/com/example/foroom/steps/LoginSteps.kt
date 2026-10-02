package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

object LoginSteps {

    fun verifyLoginScreenIsDisplayed() {
        LoginPage.waitUntilDisplayed()
    }

    fun logIn(userName: String, password: String) {
        LoginPage.enterUserName(userName)
        LoginPage.enterPassword(password)
        LoginPage.tapLogIn()
    }

    fun verifyUserNameErrorIsDisplayed() {
        LoginPage.waitForUserNameError()
    }

    fun verifyUserNameErrorIsNotDisplayed() {
        LoginPage.checkUserNameErrorNotDisplayed()
    }

    fun verifyPasswordErrorIsDisplayed() {
        LoginPage.waitForPasswordError()
    }

    fun openRegistration() {
        LoginPage.tapSignUp()
    }
}
