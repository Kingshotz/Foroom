package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.AppLanguage
import com.example.foroom.data.Constants
import com.example.foroom.data.Constants.Account
import com.example.foroom.data.DataGenerator
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.rules.TrainingAppRule
import com.example.foroom.steps.LoginSteps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Runs against the training app (com.alternator.foroom.training), the default debug build. */
@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    // Runs first: logs out, resets the language and the test account before the activity starts
    @get:Rule(order = 0)
    val trainingAppRule = TrainingAppRule()

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun changePasswordAndLogInWithIt() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(Account.USER_NAME, Account.PASSWORD)
            .checkHomeScreenIsDisplayed()
            .openProfile()
            .openChangePassword()
            .enterNewPassword(Account.NEW_PASSWORD)
            .enterRepeatPassword(Account.NEW_PASSWORD)
            .confirmPasswordChange()
            .checkLoginScreenIsDisplayed()
            .logIn(Account.USER_NAME, Account.NEW_PASSWORD)
            .checkHomeScreenIsDisplayed()
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(Account.USER_NAME, Account.PASSWORD)
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
            .logIn(Account.USER_NAME, Account.PASSWORD)
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
}
