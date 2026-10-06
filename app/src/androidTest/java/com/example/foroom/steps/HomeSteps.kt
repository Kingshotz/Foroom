package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.WAIT_TIMEOUT_SEC
import com.example.foroom.pages.HomePage

/** Steps of the home screen and its bottom navigation. */
object HomeSteps {

    fun checkHomeScreenIsDisplayed() = apply {
        onView(HomePage.navBar).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(HomePage.homeContainer).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun openProfile(): ProfileSteps {
        onView(HomePage.profileNavigation).tap(WAIT_TIMEOUT_SEC)
        return ProfileSteps
    }

    fun openCreateChat(): ChatSteps {
        onView(HomePage.createChatNavigation).tap(WAIT_TIMEOUT_SEC)
        return ChatSteps
    }

    fun openChats(): ChatSteps {
        onView(HomePage.chatsNavigation).tap(WAIT_TIMEOUT_SEC)
        return ChatSteps
    }
}
