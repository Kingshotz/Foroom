package com.example.foroom.steps

import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilGone
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.RegistrationPage

object RegistrationSteps {
    private const val AVATAR_TIMEOUT_MS = 15_000L
    private const val REGISTRATION_TIMEOUT_MS = 20_000L

    fun verifyRegistrationScreenIsDisplayed() {
        RegistrationPage.waitUntilDisplayed()
        LoginPage.logInButton.waitUntilGone()
    }

    fun fillCredentials(userName: String, password: String, repeatPassword: String = password) {
        RegistrationPage.enterUserName(userName)
        RegistrationPage.enterPassword(password)
        RegistrationPage.enterRepeatPassword(repeatPassword)
    }

    fun selectAvatar(index: Int) {
        RegistrationPage.waitForAvatarsLoaded(AVATAR_TIMEOUT_MS)
        RegistrationPage.tapAvatar(index)
        RegistrationPage.waitForAvatarSelected(index)
    }

    fun submit() {
        RegistrationPage.tapSignUp()
    }

    fun verifyHomeScreenIsDisplayed() {
        RegistrationPage.homeNavBar.waitUntil(timeoutMs = REGISTRATION_TIMEOUT_MS)
        RegistrationPage.homeContainer.waitUntil()
    }
}
