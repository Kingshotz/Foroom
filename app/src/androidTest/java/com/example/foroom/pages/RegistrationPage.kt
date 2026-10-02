package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.util.TreeIterables
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.inputEditText
import com.example.foroom.Helper.waitUntil
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object RegistrationPage {
    // Login reuses userNameInput, passwordInput and signUpButton ids, so they are
    // scoped to the registration screen through views that exist only there.
    private val onRegistrationScreen = hasSibling(withId(R.id.repeatPasswordInput))

    val userNameInput: Matcher<View> = allOf(withId(R.id.userNameInput), onRegistrationScreen)
    val passwordInput: Matcher<View> = allOf(withId(R.id.passwordInput), onRegistrationScreen)
    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)
    val avatarList: Matcher<View> = withId(R.id.listView)
    val signUpButton: Matcher<View> =
        allOf(withId(R.id.signUpButton), hasSibling(withId(R.id.logInTextView)))

    // Home screen opened after a successful sign-up
    val homeContainer: Matcher<View> = withId(R.id.homeContainer)
    val homeNavBar: Matcher<View> = withId(R.id.navBar)

    private val userNameEditText = inputEditText(userNameInput)
    private val passwordEditText = inputEditText(passwordInput)
    private val repeatPasswordEditText = inputEditText(repeatPasswordInput)

    fun avatar(index: Int): Matcher<View> = allOf(
        isDescendantOfA(avatarList),
        avatarAtIndex(index)
    )

    fun waitUntilDisplayed() {
        userNameInput.waitUntil()
        passwordInput.waitUntil()
        repeatPasswordInput.waitUntil()
        avatarList.waitUntil()
        signUpButton.waitUntil()
    }

    fun enterUserName(userName: String) {
        userNameEditText.waitUntil().perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        passwordEditText.waitUntil().perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String) {
        repeatPasswordEditText.waitUntil().perform(replaceText(password), closeSoftKeyboard())
    }

    /** Avatars are fetched asynchronously; until then the list shows blank placeholders and ignores taps. */
    fun waitForAvatarsLoaded(timeoutMs: Long) {
        avatarList.waitUntil(avatarsLoaded(), timeoutMs)
    }

    fun tapAvatar(index: Int) {
        avatar(index).waitUntil().perform(click())
    }

    fun waitForAvatarSelected(index: Int) {
        avatarList.waitUntil(hasSelectedAvatar(index))
    }

    fun tapSignUp() {
        signUpButton.waitUntil().perform(click())
    }

    private fun avatarsLoaded(): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with loaded, selectable avatars")
            }

            override fun matchesSafely(list: ImageChooserListView): Boolean =
                list.isChoosingEnabled && list.images.isNotEmpty() &&
                    list.images.none { image -> image.id == Image.BLANK_IMAGE_ID }
        }

    private fun hasSelectedAvatar(index: Int): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with avatar #$index selected")
            }

            override fun matchesSafely(list: ImageChooserListView): Boolean =
                list.selectedIndex == index
        }

    /** Matches the avatar item at [index] in display order; avatar items have no ids. */
    private fun avatarAtIndex(index: Int): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar item at index $index")
            }

            override fun matchesSafely(item: ImageChooserItemView): Boolean {
                val list = item.parent?.parent as? ImageChooserListView ?: return false
                val items = TreeIterables.breadthFirstViewTraversal(list)
                    .filterIsInstance<ImageChooserItemView>()
                return items.indexOf(item) == index
            }
        }
}
