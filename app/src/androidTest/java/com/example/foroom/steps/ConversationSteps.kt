package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.Swipe
import com.example.foroom.data.Constants.WAIT_TIMEOUT_SEC
import com.example.foroom.pages.ConversationPage
import org.hamcrest.Matcher

object ConversationSteps {
    fun checkConversationIsOpen(chatName: String) = apply {
        onView(ConversationPage.chatName(chatName)).waitUntilVisible(WAIT_TIMEOUT_SEC)
        onView(ConversationPage.messageInput).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun sendMessage(text: String) = apply {
        onView(ConversationPage.messageEditText).waitUntilVisible(WAIT_TIMEOUT_SEC).input(text)
        onView(ConversationPage.enabledSendMessageButton).tap(WAIT_TIMEOUT_SEC)
        onView(ConversationPage.emptyMessageEditText).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun sendMessages(texts: List<String>) = apply {
        texts.forEach(::sendMessage)
    }

    fun checkMessageIsDisplayed(text: String) = apply {
        onView(ConversationPage.message(text)).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun checkMessageIsDisplayed(text: String, senderName: String) = apply {
        onView(ConversationPage.messageFromSender(text, senderName)).waitUntilVisible(WAIT_TIMEOUT_SEC)
    }

    fun checkMessageIsNotVisible(text: String) = apply {
        check(!isDisplayedNow(ConversationPage.message(text))) {
            "Message \"$text\" should not be visible before scrolling to older messages"
        }
    }

    fun swipeToOlderMessage(text: String) = apply {
        val swipeArea = messageListSwipeArea()
        for (attempt in 1..Swipe.MAX_ATTEMPTS) {
            if (isDisplayedNow(ConversationPage.message(text))) break
            swiper(swipeArea.first, swipeArea.last, Swipe.DURATION_MS)
        }
        onView(ConversationPage.message(text)).check(matches(isDisplayed()))
    }

    fun closeConversation(): HomeSteps {
        onView(ConversationPage.closeButton).tap(WAIT_TIMEOUT_SEC)
        return HomeSteps
    }

    private fun messageListSwipeArea(): IntRange {
        var swipeArea = IntRange.EMPTY
        onView(ConversationPage.messagesRecyclerView).waitUntilVisible(WAIT_TIMEOUT_SEC).check { view, noViewFound ->
            if (view == null) throw noViewFound
            swipeArea = swipeAreaOf(view)
        }
        return swipeArea
    }

    private fun swipeAreaOf(list: View): IntRange {
        val location = IntArray(2)
        list.getLocationOnScreen(location)
        val (left, top) = location
        check(Swipe.SWIPER_X in left until left + list.width) {
            "The swiper's x coordinate ${Swipe.SWIPER_X} is outside the message list ($left..${left + list.width})"
        }

        val contentTop = top + list.paddingTop
        val contentBottom = top + list.height - list.paddingBottom
        val edgeOffset = ((contentBottom - contentTop) * Swipe.EDGE_OFFSET_RATIO).toInt()
        return (contentTop + edgeOffset)..(contentBottom - edgeOffset)
    }

    private fun isDisplayedNow(matcher: Matcher<View>): Boolean = try {
        onView(matcher).check(matches(isDisplayed()))
        true
    } catch (_: NoMatchingViewException) {
        false
    } catch (_: AssertionError) {
        false
    }
}
