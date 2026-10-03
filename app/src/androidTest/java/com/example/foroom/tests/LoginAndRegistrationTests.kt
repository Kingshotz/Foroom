package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.data.Constants
import com.example.foroom.data.DataGenerator
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

/** Runs against the training app (com.alternator.foroom.training), the default debug build. */
@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    // Runs before the activity starts: logs out any saved user so every test begins on the login screen
    @get:Rule(order = 0)
    val logOutRule = object : ExternalResource() {
        override fun before() {
            val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
            check(packageName == Constants.TRAINING_PACKAGE) {
                "Run these tests on the training build, not $packageName"
            }

            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun validUserNameAndInvalidPassword() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(Constants.EXISTING_USER_NAME)
            .enterPassword(Constants.WRONG_PASSWORD)
            .clickLogIn()
            .checkPasswordError(Constants.PASSWORD_ERROR)
    }

    @Test
    fun invalidUserNameAndInvalidPassword() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(DataGenerator.unknownUserName())
            .enterPassword(Constants.WRONG_PASSWORD)
            .clickLogIn()
            .checkUserNameError(Constants.USER_NAME_ERROR)
            .checkPasswordError(Constants.PASSWORD_ERROR)
    }

    @Test
    fun successfulRegistration() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .clickSignUp()
            .checkRegistrationScreenIsDisplayed()
            .enterUserName(DataGenerator.newUserName())
            .enterPassword(Constants.NEW_PASSWORD)
            .enterRepeatPassword(Constants.NEW_PASSWORD)
            .selectAvatar(Constants.AVATAR_INDEX)
            .clickSignUp()
            .checkHomeScreenIsDisplayed()
    }
}
