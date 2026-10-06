package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object LoginPage {
    val userNameInput = withId(R.id.userNameInput)
    val passwordInput = withId(R.id.passwordInput)
    val logInButton = withId(R.id.logInButton)
    val signUpButton = withId(R.id.signUpButton)

    val userNameEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    val passwordEditText = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))

    val userNameError = allOf(withId(DesignR.id.descriptionTextView), isDescendantOfA(userNameInput))
    val passwordError = allOf(withId(DesignR.id.descriptionTextView), isDescendantOfA(passwordInput))
}
