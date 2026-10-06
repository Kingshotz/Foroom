package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.childOf
import com.example.design_system.R as DesignR

/** Change password bottom sheet. */
object ChangePasswordPage {
    val passwordInput = withId(R.id.passwordInput)
    val repeatPasswordInput = withId(R.id.repeatPasswordInput)

    val passwordEditText = childOf(passwordInput, DesignR.id.inputEditText)
    val repeatPasswordEditText = childOf(repeatPasswordInput, DesignR.id.inputEditText)

    // Confirm button of the bottom sheet, declared in the design system
    val confirmButton = withId(DesignR.id.actionButton)
}
