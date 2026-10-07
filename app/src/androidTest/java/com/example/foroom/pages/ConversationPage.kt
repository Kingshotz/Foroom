package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.components.message.ForoomMessageView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ConversationPage {
    val messagesRecyclerView = withId(R.id.messagesRecyclerView)
    val messageInput = withId(R.id.messageInput)
    val closeButton = withId(R.id.closeButton)

    val messageEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(messageInput))
    val emptyMessageEditText = allOf(messageEditText, withText(""))

    val sendMessageButton = allOf(withId(R.id.sendMessageButton), isDescendantOfA(messageInput))
    val enabledSendMessageButton = allOf(sendMessageButton, isEnabled())

    fun chatName(name: String): Matcher<View> = allOf(withId(DesignR.id.chatNameTextView), withText(name))

    fun message(text: String): Matcher<View> = allOf(
        withId(DesignR.id.messageTextView),
        withText(text),
        isDescendantOfA(messagesRecyclerView)
    )

    fun messageFromSender(text: String, senderName: String): Matcher<View> = allOf(
        isAssignableFrom(ForoomMessageView::class.java),
        hasDescendant(allOf(withId(DesignR.id.messageTextView), withText(text))),
        hasDescendant(allOf(withId(DesignR.id.userNameTextView), withText(senderName)))
    )
}
