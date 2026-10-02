package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.withIndex
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object RegistrationPage {
    val userNameInput = withId(R.id.userNameInput)
    val passwordInput = withId(R.id.passwordInput)
    val repeatPasswordInput = withId(R.id.repeatPasswordInput)
    val avatarList = withId(R.id.listView)
    val signUpButton = withId(R.id.signUpButton)

    val userNameEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    val passwordEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    // Home screen, opened after a successful registration
    val homeContainer = withId(R.id.homeContainer)
    val navBar = withId(R.id.navBar)

    /** True when the real avatars are shown (not the loading placeholders) and can be selected. */
    val avatarsLoaded: Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatars are loaded")
            }

            override fun matchesSafely(list: ImageChooserListView) =
                list.isChoosingEnabled && list.images.isNotEmpty() &&
                    list.images.none { it.id == Image.BLANK_IMAGE_ID }
        }

    fun enterUserName(userName: String) {
        onView(userNameEditText).perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        onView(passwordEditText).perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String) {
        onView(repeatPasswordEditText).perform(replaceText(password), closeSoftKeyboard())
    }

    fun clickAvatar(index: Int) {
        val avatars = allOf(isAssignableFrom(ImageChooserItemView::class.java), isDescendantOfA(avatarList))
        onView(withIndex(avatars, index)).perform(click())
    }

    fun clickSignUp() {
        onView(signUpButton).perform(click())
    }
}
