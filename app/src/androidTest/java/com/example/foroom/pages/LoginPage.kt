package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.childOf
import com.example.design_system.R as DesignR

object LoginPage {
    val userNameInput = withId(R.id.userNameInput)
    val passwordInput = withId(R.id.passwordInput)
    val logInButton = withId(R.id.logInButton)
    val signUpButton = withId(R.id.signUpButton)

    val userNameEditText = childOf(userNameInput, DesignR.id.inputEditText)
    val passwordEditText = childOf(passwordInput, DesignR.id.inputEditText)

    val userNameError = childOf(userNameInput, DesignR.id.descriptionTextView)
    val passwordError = childOf(passwordInput, DesignR.id.descriptionTextView)
}
