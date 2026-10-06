package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.childOf
import com.example.foroom.Helper.hasLoadedImages
import com.example.foroom.Helper.imageAtIndex
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object CreateChatPage {
    val chatNameInput = withId(R.id.chatNameInput)
    val chatImageChooser = withId(R.id.chatImageChooser)
    val createChatButton = withId(R.id.createChatButton)
    val closeButton = withId(R.id.closeButton)

    val chatNameEditText = childOf(chatNameInput, DesignR.id.inputEditText)

    /** The image chooser once its images are loaded and can be selected. */
    val loadedImageChooser: Matcher<View> = allOf(chatImageChooser, hasLoadedImages())

    fun chatImage(index: Int): Matcher<View> = allOf(imageAtIndex(index), isDescendantOfA(chatImageChooser))
}
