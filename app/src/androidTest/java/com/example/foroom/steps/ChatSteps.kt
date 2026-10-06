package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.WAIT_TIMEOUT_SEC
import com.example.foroom.pages.ChatPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

object ChatSteps {
    fun checkCreateChatScreenIsDisplayed() = apply {
        onView(CreateChatPage.chatNameInput).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(CreateChatPage.createChatButton).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun enterChatName(name: String) = apply {
        onView(CreateChatPage.chatNameEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(name)
    }

    fun selectChatImage(index: Int) = apply {
        onView(CreateChatPage.loadedImageChooser).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(CreateChatPage.chatImage(index)).tap(WAIT_TIMEOUT_SEC)
    }

    fun clickCreateChat() = apply {
        onView(CreateChatPage.createChatButton).tap(WAIT_TIMEOUT_SEC)
    }

    fun checkChatScreenIsDisplayed(name: String) = apply {
        onView(ChatPage.chatName(name)).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ChatPage.closeButton).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun closeChat() = apply {
        onView(ChatPage.closeButton).tap(WAIT_TIMEOUT_SEC)
    }

    fun searchChat(name: String) = apply {
        onView(ChatsPage.searchEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(name)
    }

    fun checkChatIsListed(name: String) = apply {
        onView(ChatsPage.chatCard(name)).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }
}
