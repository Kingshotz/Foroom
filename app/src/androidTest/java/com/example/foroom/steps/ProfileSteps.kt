package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.AppLanguage
import com.example.foroom.data.Constants.WAIT_TIMEOUT_SEC
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

object ProfileSteps {
    fun checkProfileScreenIsDisplayed() = apply {
        onView(ProfilePage.changePasswordItem).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ProfilePage.changeLanguageItem).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ProfilePage.signOutItem).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun openChangePassword() = apply {
        onView(ProfilePage.changePasswordItem).tap(WAIT_TIMEOUT_SEC)
    }

    fun enterNewPassword(password: String) = apply {
        onView(ChangePasswordPage.passwordEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(password)
    }

    fun enterRepeatPassword(password: String) = apply {
        onView(ChangePasswordPage.repeatPasswordEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(password)
    }

    fun confirmPasswordChange(): LoginSteps {
        onView(ChangePasswordPage.confirmButton).tap(WAIT_TIMEOUT_SEC)
        return LoginSteps
    }

    fun openChangeLanguage() = apply {
        onView(ProfilePage.changeLanguageItem).tap(WAIT_TIMEOUT_SEC)
    }

    fun selectLanguage(language: AppLanguage) = apply {
        onView(ChangeLanguagePage.languageButton(language)).tap(WAIT_TIMEOUT_SEC)
    }

    fun changeLanguage(language: AppLanguage) = apply {
        openChangeLanguage()
        selectLanguage(language)
    }

    fun checkProfileLanguage(language: AppLanguage) = apply {
        onView(ProfilePage.changeLanguageLabel(language.changeLanguageLabel)).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ProfilePage.signOutLabel(language.signOutLabel)).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }
}
