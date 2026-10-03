package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
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
        onView(RegistrationPage.userNameEditText).perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) = apply {
        onView(RegistrationPage.passwordEditText).perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String) = apply {
        onView(RegistrationPage.repeatPasswordEditText).perform(replaceText(password), closeSoftKeyboard())
    }

    fun selectAvatar(index: Int) = apply {
        waitFor(RegistrationPage.avatarList, matches(RegistrationPage.avatarsLoaded))
        onView(RegistrationPage.avatar(index)).perform(click())
    }

    fun clickSignUp() = apply {
        onView(RegistrationPage.signUpButton).perform(click())
    }

    fun checkHomeScreenIsDisplayed() = apply {
        waitFor(RegistrationPage.navBar)
        waitFor(RegistrationPage.homeContainer)
    }
}
