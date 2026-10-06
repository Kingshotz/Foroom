package com.example.foroom.rules

import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.data.Constants
import com.example.foroom.data.TrainingBackend
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import kotlinx.coroutines.runBlocking
import org.junit.rules.ExternalResource
import org.koin.core.context.GlobalContext

/**
 * Puts the training app into a known state before ForoomActivity starts, so every test is independent:
 * - nobody is logged in and the language is the default one;
 * - the dedicated test account exists and has its original password.
 * Use it with a lower order than the ActivityScenarioRule.
 */
class TrainingAppRule : ExternalResource() {

    override fun before() {
        val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
        check(packageName == Constants.TRAINING_PACKAGE) {
            "Run these tests on the training build, not $packageName"
        }

        runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        TrainingBackend.saveAccount(
            Constants.Account.USER_NAME,
            Constants.Account.PASSWORD,
            Constants.Account.AVATAR_ID
        )
    }
}
