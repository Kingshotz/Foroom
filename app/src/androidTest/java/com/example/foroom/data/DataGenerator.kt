package com.example.foroom.data

import com.example.foroom.models.User

/** Generates test data that must be different on every run. */
object DataGenerator {

    /** A new user with a unique name, so the account can be registered again on every run. */
    fun user() = User(
        userName = "${Constants.Account.USER_NAME_PREFIX}${System.currentTimeMillis()}",
        password = Constants.Account.PASSWORD
    )

    /** Chat name made of the student's full name and a timestamp, so each run finds only its own chat. */
    fun chatName() = "${Constants.Chat.OWNER_FULL_NAME} ${System.currentTimeMillis()}"
}
