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

/** Steps of the profile screen and its change password / change language bottom sheets. */
object ProfileSteps {

    fun checkProfileScreenIsDisplayed() = apply {
        onView(ProfilePage.changePasswordItem).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ProfilePage.changeLanguageItem).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ProfilePage.signOutItem).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    // Change password

    fun openChangePassword() = apply {
        onView(ProfilePage.changePasswordItem).tap(WAIT_TIMEOUT_SEC)
    }

    fun enterNewPassword(password: String) = apply {
        onView(ChangePasswordPage.passwordEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(password)
    }

    fun enterRepeatPassword(password: String) = apply {
        onView(ChangePasswordPage.repeatPasswordEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(password)
    }

    /** The app signs the user out after the password is changed and returns to the login screen. */
    fun confirmPasswordChange(): LoginSteps {
        onView(ChangePasswordPage.confirmButton).tap(WAIT_TIMEOUT_SEC)
        return LoginSteps
    }

    // Change language

    fun openChangeLanguage() = apply {
        onView(ProfilePage.changeLanguageItem).tap(WAIT_TIMEOUT_SEC)
    }

    fun selectLanguage(language: AppLanguage) = apply {
        onView(ChangeLanguagePage.languageButton(language)).tap(WAIT_TIMEOUT_SEC)
    }

    /** Opens the language selector and chooses [language]. */
    fun changeLanguage(language: AppLanguage) = apply {
        openChangeLanguage()
        selectLanguage(language)
    }

    /** The app restarts its screen after a language change, so the labels are awaited, not just read. */
    fun checkProfileLanguage(language: AppLanguage) = apply {
        onView(ProfilePage.changeLanguageLabel(language.changeLanguageLabel)).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ProfilePage.signOutLabel(language.signOutLabel)).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }
}
