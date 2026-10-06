package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ChatPage {
    val closeButton = withId(R.id.closeButton)

    fun chatName(name: String): Matcher<View> = allOf(withId(DesignR.id.chatNameTextView), withText(name))
}
