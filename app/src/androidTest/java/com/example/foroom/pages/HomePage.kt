package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

object HomePage {
    val homeContainer = withId(R.id.homeContainer)
    val navBar = withId(R.id.navBar)

    val chatsNavigation = withId(R.id.homeNavigationChats)
    val createChatNavigation = withId(R.id.homeNavigationCreateChat)
    val profileNavigation = withId(R.id.homeNavigationProfile)
}
