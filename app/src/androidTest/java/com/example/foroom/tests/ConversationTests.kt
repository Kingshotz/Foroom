package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.data.Constants
import com.example.foroom.data.Constants.Chat
import com.example.foroom.data.Constants.Messages
import com.example.foroom.data.DataGenerator
import com.example.foroom.domain.model.request.RegistrationRequest
import com.example.foroom.domain.usecase.CreateChatUseCase
import com.example.foroom.domain.usecase.GetChatsUseCase
import com.example.foroom.domain.usecase.RegisterUserUseCase
import com.example.foroom.models.User
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.shared.util.runtime.user_token.UserTokenRuntimeHolder
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ConversationTests {
    @get:Rule(order = 0)
    val logOutRule = object : ExternalResource() {
        override fun before() {
            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun sendMessageInJohnWeekAndSeeItAfterReopeningChat() {
        val message = DataGenerator.withUniqueSuffix(Messages.DRINK)

        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(userA)
            .checkHomeScreenIsDisplayed()
            .openChats()
            .openChat(Chat.JOHN_WEEK)
            .checkConversationIsOpen(Chat.JOHN_WEEK)
            .sendMessage(message)
            .checkMessageIsDisplayed(message)
            .closeConversation()
            .openChats()
            .openChat(Chat.JOHN_WEEK)
            .checkConversationIsOpen(Chat.JOHN_WEEK)
            .checkMessageIsDisplayed(message)
    }

    @Test
    fun sendQuestionAboutAcademyModuleInOwnChat() {
        val question = DataGenerator.withUniqueSuffix(Messages.QUESTION)

        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(userA)
            .checkHomeScreenIsDisplayed()
            .openChats()
            .openChat(fullNameChat)
            .checkConversationIsOpen(fullNameChat)
            .sendMessage(question)
            .checkMessageIsDisplayed(question)
    }

    @Test
    fun continueSharedConversationWithAnotherAccount() {
        val suffix = DataGenerator.uniqueSuffix()
        val greeting = "${Messages.GREETING} $suffix"
        val reply = "${Messages.REPLY} $suffix"

        LoginSteps
            .checkLoginScreenIsDisplayed()
            .logIn(userA)
            .checkHomeScreenIsDisplayed()
            .openChats()
            .openChat(sharedChat)
            .checkConversationIsOpen(sharedChat)
            .sendMessage(greeting)
            .checkMessageIsDisplayed(greeting)
            .sendMessages(DataGenerator.additionalMessages(suffix))
            .closeConversation()
            .openProfile()
            .signOut()
            .checkLoginScreenIsDisplayed()
            .logIn(userB)
            .checkHomeScreenIsDisplayed()
            .openChats()
            .openChat(sharedChat)
            .checkConversationIsOpen(sharedChat)
            .checkMessageIsNotVisible(greeting)
            .swipeToOlderMessage(greeting)
            .checkMessageIsDisplayed(greeting, userA.userName)
            .sendMessage(reply)
            .checkMessageIsDisplayed(reply)
            .closeConversation()
            .openProfile()
            .signOut()
            .checkLoginScreenIsDisplayed()
            .logIn(userA)
            .checkHomeScreenIsDisplayed()
            .openChats()
            .openChat(sharedChat)
            .checkConversationIsOpen(sharedChat)
            .checkMessageIsDisplayed(reply, userB.userName)
    }

    companion object {
        private val userA = DataGenerator.user(Constants.Account.USER_A_PREFIX)
        private val userB = DataGenerator.user(Constants.Account.USER_B_PREFIX)
        private val fullNameChat = DataGenerator.chatName()
        private val sharedChat = DataGenerator.sharedChatName()

        @BeforeClass
        @JvmStatic
        fun prepareTestData() {
            val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
            check(packageName == Constants.TRAINING_PACKAGE) {
                "Run these tests on the training build, not $packageName"
            }

            val koin = GlobalContext.get()
            val tokenHolder = koin.get<UserTokenRuntimeHolder>()
            val createChat = koin.get<CreateChatUseCase>()

            runBlocking {
                val userAToken = register(userA)
                register(userB)

                tokenHolder.setUserToken(userAToken)
                val johnWeekExists = koin.get<GetChatsUseCase>()(page = 0, name = Chat.JOHN_WEEK).chats
                    .any { chat -> chat.name == Chat.JOHN_WEEK }
                if (!johnWeekExists) createChat(Chat.JOHN_WEEK, Chat.EMOJI_ID)
                createChat(fullNameChat, Chat.EMOJI_ID)
                createChat(sharedChat, Chat.EMOJI_ID)
                tokenHolder.setUserToken("")
            }
        }

        private suspend fun register(user: User): String {
            val registerUser = GlobalContext.get().get<RegisterUserUseCase>()
            return registerUser(RegistrationRequest(user.userName, user.password, Constants.Account.AVATAR_ID)).token
        }
    }
}
