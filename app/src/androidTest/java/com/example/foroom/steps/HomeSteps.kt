package com.example.foroom.steps

import com.example.foroom.Helper.click
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.HomePage

/** Steps of the home screen and its bottom navigation. */
object HomeSteps {

    fun checkHomeScreenIsDisplayed() = apply {
        HomePage.navBar.waitUntilDisplayed()
        HomePage.homeContainer.waitUntilDisplayed()
    }

    fun openProfile(): ProfileSteps {
        HomePage.profileNavigation.click()
        return ProfileSteps
    }

    fun openCreateChat(): ChatSteps {
        HomePage.createChatNavigation.click()
        return ChatSteps
    }

    fun openChats(): ChatSteps {
        HomePage.chatsNavigation.click()
        return ChatSteps
    }
}
