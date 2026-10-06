package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.data.AppLanguage
import com.example.foroom.data.Constants
import com.example.foroom.data.DataGenerator
import com.example.foroom.domain.model.request.RegistrationRequest
import com.example.foroom.domain.usecase.RegisterUserUseCase
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {
    @get:Rule(order = 0)
    val logOutRule = object : ExternalResource() {
        override fun before() {
            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun changePasswordAndLogInWithIt() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(user)
            .checkHomeScreenIsDisplayed()
            .openProfile()
            .openChangePassword()
            .enterNewPassword(Constants.Account.NEW_PASSWORD)
            .enterRepeatPassword(Constants.Account.NEW_PASSWORD)
            .confirmPasswordChange()

        user.password = Constants.Account.NEW_PASSWORD

        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(user)
            .checkHomeScreenIsDisplayed()
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(user)
            .checkHomeScreenIsDisplayed()
            .openProfile()
            .changeLanguage(AppLanguage.GEORGIAN)
            .checkProfileLanguage(AppLanguage.GEORGIAN)
            .changeLanguage(AppLanguage.ENGLISH)
            .checkProfileLanguage(AppLanguage.ENGLISH)
            .changeLanguage(AppLanguage.GEORGIAN)
            .checkProfileLanguage(AppLanguage.GEORGIAN)
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = DataGenerator.chatName()

        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(user)
            .checkHomeScreenIsDisplayed()
            .openCreateChat()
            .enterChatName(chatName)
            .selectChatImage(Constants.Chat.IMAGE_INDEX)
            .clickCreateChat()
            .checkChatScreenIsDisplayed(chatName)
            .closeChat()
            .searchChat(chatName)
            .checkChatIsListed(chatName)
    }

    companion object {
        private val user = DataGenerator.user()

        @BeforeClass
        @JvmStatic
        fun registerUser() {
            val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
            check(packageName == Constants.TRAINING_PACKAGE) {
                "Run these tests on the training build, not $packageName"
            }

            val registerUser = GlobalContext.get().get<RegisterUserUseCase>()
            runBlocking {
                registerUser(RegistrationRequest(user.userName, user.password, Constants.Account.AVATAR_ID))
            }
        }
    }
}
