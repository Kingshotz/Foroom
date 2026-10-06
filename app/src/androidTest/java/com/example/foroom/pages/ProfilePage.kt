package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.childOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ProfilePage {
    val changePasswordItem = withId(R.id.changePasswordItem)
    val changeLanguageItem = withId(R.id.changeLanguageItem)
    val signOutItem = withId(R.id.signOutItem)

    // The text of a list item is a child of the item
    private val changeLanguageLabel = childOf(changeLanguageItem, DesignR.id.listItemTextView)
    private val signOutLabel = childOf(signOutItem, DesignR.id.listItemTextView)

    fun changeLanguageLabel(text: String): Matcher<View> = allOf(changeLanguageLabel, withText(text))

    fun signOutLabel(text: String): Matcher<View> = allOf(signOutLabel, withText(text))
}
