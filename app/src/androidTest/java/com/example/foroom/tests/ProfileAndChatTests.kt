package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.AppLanguage
import com.example.foroom.data.Constants
import com.example.foroom.data.DataGenerator
import com.example.foroom.domain.model.request.RegistrationRequest
import com.example.foroom.domain.usecase.RegisterUserUseCase
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.rules.TrainingAppRule
import com.example.foroom.steps.LoginSteps
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

/** Runs against the training app (com.alternator.foroom.training), the default debug build. */
@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    // Runs first: logs out and resets the language before the activity starts
    @get:Rule(order = 0)
    val trainingAppRule = TrainingAppRule()

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

        // The account has the new password now; the other tests log in with the user's current password
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
        // One new user for the whole class; the app keeps its accounts on the device
        private val user = DataGenerator.user()

        @BeforeClass
        @JvmStatic
        fun registerUser() {
            val registerUser = GlobalContext.get().get<RegisterUserUseCase>()
            runBlocking {
                registerUser(RegistrationRequest(user.userName, user.password, Constants.Account.AVATAR_ID))
            }
        }
    }
}
