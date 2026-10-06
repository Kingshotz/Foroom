package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.util.TreeIterables
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches an image chooser that shows its real images. Until they are loaded
 * the chooser holds blank placeholders, which must not be selected.
 */
fun hasLoadedImages(): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image chooser with loaded images")
        }

        override fun matchesSafely(list: ImageChooserListView) =
            list.images.isNotEmpty() && list.images.none { image -> image.id == Image.BLANK_IMAGE_ID }
    }

/** Matches the image at [index] (in display order) of an image chooser; its items have no ids. */
fun imageAtIndex(index: Int): Matcher<View> =
    object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image chooser item at index $index")
        }

        override fun matchesSafely(item: ImageChooserItemView): Boolean {
            val list = item.parent?.parent as? ImageChooserListView ?: return false
            val items = TreeIterables.breadthFirstViewTraversal(list).filterIsInstance<ImageChooserItemView>()
            return items.indexOf(item) == index
        }
    }
