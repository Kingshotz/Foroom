package com.example.foroom.steps

import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.example.foroom.Helper.waitFor
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.RegistrationPage

/** Every step returns the steps object, so calls can be chained. */
object RegistrationSteps {

    fun checkRegistrationScreenIsDisplayed() = apply {
        waitFor(RegistrationPage.repeatPasswordInput)
        waitFor(RegistrationPage.avatarList)
        // Both screens use the same ids, so wait until the login screen is fully gone
        waitFor(LoginPage.logInButton, doesNotExist())
    }

    fun enterUserName(userName: String) = apply {
        RegistrationPage.enterUserName(userName)
    }

    fun enterPassword(password: String) = apply {
        RegistrationPage.enterPassword(password)
    }

    fun enterRepeatPassword(password: String) = apply {
        RegistrationPage.enterRepeatPassword(password)
    }

    fun selectAvatar(index: Int) = apply {
        waitFor(RegistrationPage.avatarList, matches(RegistrationPage.avatarsLoaded))
        RegistrationPage.clickAvatar(index)
    }

    fun clickSignUp() = apply {
        RegistrationPage.clickSignUp()
    }

    fun checkHomeScreenIsDisplayed() = apply {
        waitFor(RegistrationPage.navBar)
        waitFor(RegistrationPage.homeContainer)
    }
}
