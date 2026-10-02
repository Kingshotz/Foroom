package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginAndRegistrationTests {

    /**
     * Clears the saved session before the activity starts, so every test opens on the login
     * screen regardless of which tests ran before it (e.g. a successful registration).
     */
    @get:Rule(order = 0)
    val signedOutRule = object : ExternalResource() {
        override fun before() {
            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun logInWithValidUserNameAndInvalidPasswordShowsPasswordError() {
        LoginSteps.verifyLoginScreenIsDisplayed()
        LoginSteps.logIn(validUserName, INVALID_PASSWORD)
        LoginSteps.verifyPasswordErrorIsDisplayed()
        LoginSteps.verifyUserNameErrorIsNotDisplayed()
    }

    @Test
    fun logInWithInvalidUserNameAndInvalidPasswordShowsUserNameAndPasswordErrors() {
        LoginSteps.verifyLoginScreenIsDisplayed()
        LoginSteps.logIn(uniqueUserName("nouser"), INVALID_PASSWORD)
        LoginSteps.verifyUserNameErrorIsDisplayed()
        LoginSteps.verifyPasswordErrorIsDisplayed()
    }

    @Test
    fun signUpWithValidDataOpensHomeScreen() {
        LoginSteps.verifyLoginScreenIsDisplayed()
        LoginSteps.openRegistration()
        RegistrationSteps.verifyRegistrationScreenIsDisplayed()
        RegistrationSteps.fillCredentials(uniqueUserName("qa"), VALID_PASSWORD)
        RegistrationSteps.selectAvatar(AVATAR_INDEX)
        RegistrationSteps.submit()
        RegistrationSteps.verifyHomeScreenIsDisplayed()
    }

    private companion object {
        /** Seeded in training mode; pass `-e validUserName <name>` to use another existing account. */
        const val DEFAULT_VALID_USER_NAME = "student"
        const val INVALID_PASSWORD = "WrongPass987!"
        const val VALID_PASSWORD = "Passw0rd123!"

        /** The first avatar is preselected, so pick another one to exercise the selection. */
        const val AVATAR_INDEX = 1

        val validUserName: String =
            InstrumentationRegistry.getArguments().getString("validUserName")
                ?: DEFAULT_VALID_USER_NAME

        fun uniqueUserName(prefix: String) = "$prefix${System.currentTimeMillis()}"
    }
}
