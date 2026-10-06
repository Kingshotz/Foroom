package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.util.TreeIterables
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object CreateChatPage {
    val chatNameInput = withId(R.id.chatNameInput)
    val chatImageChooser = withId(R.id.chatImageChooser)
    val createChatButton = withId(R.id.createChatButton)
    val closeButton = withId(R.id.closeButton)

    val chatNameEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(chatNameInput))

    /** The image chooser once its real images are loaded; until then it holds blank placeholders. */
    val loadedImageChooser: Matcher<View> = allOf(
        chatImageChooser,
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("image chooser with loaded images")
            }

            override fun matchesSafely(list: ImageChooserListView) =
                list.images.isNotEmpty() && list.images.none { image -> image.id == Image.BLANK_IMAGE_ID }
        }
    )

    /** The image at [index] (in display order) of the chooser; its items have no ids. */
    fun chatImage(index: Int): Matcher<View> = allOf(
        isDescendantOfA(chatImageChooser),
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("chat image at index $index")
            }

            override fun matchesSafely(item: ImageChooserItemView): Boolean {
                val list = item.parent?.parent as? ImageChooserListView ?: return false
                val items = TreeIterables.breadthFirstViewTraversal(list).filterIsInstance<ImageChooserItemView>()
                return items.indexOf(item) == index
            }
        }
    )
}
