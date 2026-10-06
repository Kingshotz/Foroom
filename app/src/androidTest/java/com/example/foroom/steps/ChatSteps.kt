package com.example.foroom.steps

import com.example.foroom.Helper.click
import com.example.foroom.Helper.enterText
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.ChatPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

/** Steps of chat creation, the opened chat and the chat list. */
object ChatSteps {

    fun checkCreateChatScreenIsDisplayed() = apply {
        CreateChatPage.chatNameInput.waitUntilDisplayed()
        CreateChatPage.createChatButton.waitUntilDisplayed()
    }

    fun enterChatName(name: String) = apply {
        CreateChatPage.chatNameEditText.enterText(name)
    }

    /** Chat images are loaded in the background, so the selection waits until they are ready. */
    fun selectChatImage(index: Int) = apply {
        CreateChatPage.loadedImageChooser.waitUntilDisplayed()
        CreateChatPage.chatImage(index).click()
    }

    fun clickCreateChat() = apply {
        CreateChatPage.createChatButton.click()
    }

    fun checkChatScreenIsDisplayed(name: String) = apply {
        ChatPage.chatName(name).waitUntilDisplayed()
        ChatPage.closeButton.waitUntilDisplayed()
    }

    fun closeChat() = apply {
        ChatPage.closeButton.click()
    }

    fun searchChat(name: String) = apply {
        ChatsPage.searchEditText.enterText(name)
    }

    fun checkChatIsListed(name: String) = apply {
        ChatsPage.chatCard(name).waitUntilDisplayed()
    }
}
