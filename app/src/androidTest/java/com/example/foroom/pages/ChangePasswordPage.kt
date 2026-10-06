package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

/** Change password bottom sheet. */
object ChangePasswordPage {
    val passwordInput = withId(R.id.passwordInput)
    val repeatPasswordInput = withId(R.id.repeatPasswordInput)

    val passwordEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    // Confirm button of the bottom sheet, declared in the design system
    val confirmButton = withId(DesignR.id.actionButton)
}
