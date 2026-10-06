package com.example.foroom.steps

import com.example.foroom.Helper.click
import com.example.foroom.Helper.enterText
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.data.AppLanguage
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

/** Steps of the profile screen and its change password / change language bottom sheets. */
object ProfileSteps {

    fun checkProfileScreenIsDisplayed() = apply {
        ProfilePage.changePasswordItem.waitUntilDisplayed()
        ProfilePage.changeLanguageItem.waitUntilDisplayed()
        ProfilePage.signOutItem.waitUntilDisplayed()
    }

    // Change password

    fun openChangePassword() = apply {
        ProfilePage.changePasswordItem.click()
    }

    fun enterNewPassword(password: String) = apply {
        ChangePasswordPage.passwordEditText.enterText(password)
    }

    fun enterRepeatPassword(password: String) = apply {
        ChangePasswordPage.repeatPasswordEditText.enterText(password)
    }

    /** The app signs the user out after the password is changed and returns to the login screen. */
    fun confirmPasswordChange(): LoginSteps {
        ChangePasswordPage.confirmButton.click()
        return LoginSteps
    }

    // Change language

    fun openChangeLanguage() = apply {
        ProfilePage.changeLanguageItem.click()
    }

    fun selectLanguage(language: AppLanguage) = apply {
        ChangeLanguagePage.languageButton(language).click()
    }

    /** Opens the language selector and chooses [language]. */
    fun changeLanguage(language: AppLanguage) = apply {
        openChangeLanguage()
        selectLanguage(language)
    }

    /** The app restarts its screen after a language change, so the labels are awaited, not just read. */
    fun checkProfileLanguage(language: AppLanguage) = apply {
        ProfilePage.changeLanguageLabel(language.changeLanguageLabel).waitUntilDisplayed()
        ProfilePage.signOutLabel(language.signOutLabel).waitUntilDisplayed()
    }
}
