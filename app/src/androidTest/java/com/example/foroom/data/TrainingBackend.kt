package com.example.foroom.data

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.json.JSONObject
import java.security.MessageDigest
import java.util.UUID

/**
 * Test access to the local backend of the training app (TrainingInterceptor).
 * Accounts are stored on the device, so tests prepare them directly instead of
 * depending on an account that was registered by hand.
 */
object TrainingBackend {
    private const val PREFERENCES_NAME = "foroom_training"
    private const val USERS_KEY = "users"

    /** Creates the account or, if it exists, resets its password. Chats of an existing account are kept. */
    fun saveAccount(userName: String, password: String, avatarId: Int) {
        val preferences = InstrumentationRegistry.getInstrumentation().targetContext
            .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val users = JSONObject(preferences.getString(USERS_KEY, "{}")!!)
        val salt = UUID.randomUUID().toString()

        val account = JSONObject()
            .put("id", users.optJSONObject(userName)?.optString("id") ?: UUID.randomUUID().toString())
            .put("userName", userName)
            .put("salt", salt)
            .put("passwordHash", hash(password, salt))
            .put("avatarId", avatarId)
        users.put(userName, account)

        check(preferences.edit().putString(USERS_KEY, users.toString()).commit()) {
            "Could not save the training account $userName"
        }
    }

    private fun hash(password: String, salt: String): String =
        MessageDigest.getInstance("SHA-256").digest((salt + password).toByteArray())
            .joinToString("") { "%02x".format(it) }
}
