package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

/**
 * Runs against the training app (com.alternator.foroom.training), the default debug build.
 * Test data and error texts come from its local backend (TrainingInterceptor).
 */
@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    // Runs before the activity starts: logs out any saved user so every test begins on the login screen
    @get:Rule(order = 0)
    val logOutRule = object : ExternalResource() {
        override fun before() {
            val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
            check(packageName == TRAINING_PACKAGE) { "Run these tests on the training build, not $packageName" }

            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun validUserNameAndInvalidPassword() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(EXISTING_USER_NAME)
            .enterPassword(WRONG_PASSWORD)
            .clickLogIn()
            .checkPasswordError(PASSWORD_ERROR)
    }

    @Test
    fun invalidUserNameAndInvalidPassword() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName("nouser${System.currentTimeMillis()}")
            .enterPassword(WRONG_PASSWORD)
            .clickLogIn()
            .checkUserNameError(USER_NAME_ERROR)
            .checkPasswordError(PASSWORD_ERROR)
    }

    @Test
    fun successfulRegistration() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .clickSignUp()
            .checkRegistrationScreenIsDisplayed()
            .enterUserName("user${System.currentTimeMillis()}")
            .enterPassword(NEW_PASSWORD)
            .enterRepeatPassword(NEW_PASSWORD)
            .selectAvatar(1)
            .clickSignUp()
            .checkHomeScreenIsDisplayed()
    }

    companion object {
        const val TRAINING_PACKAGE = "com.alternator.foroom.training"

        // Demo account that the training app creates automatically
        const val EXISTING_USER_NAME = "student"
        const val WRONG_PASSWORD = "WrongPassword1"
        const val NEW_PASSWORD = "Test12345"

        const val USER_NAME_ERROR = "Username does not exist"
        const val PASSWORD_ERROR = "Incorrect password"
    }
}
