package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

/** Chat list with its search bar. */
object ChatsPage {
    val searchChatInput = withId(R.id.searchChatInput)
    val chatsRecyclerView = withId(R.id.chatsRecyclerView)

    // The search bar wraps an Input, so its edit text is found by the Input's child id
    val searchEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(searchChatInput))

    /** The title of the chat card with this name, inside the chat list. */
    fun chatCard(name: String): Matcher<View> = allOf(
        withId(DesignR.id.chatTitleTextView),
        withText(name),
        isDescendantOfA(chatsRecyclerView)
    )
}
