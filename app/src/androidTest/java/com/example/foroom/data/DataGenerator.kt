package com.example.foroom.data

import com.example.foroom.models.User

object DataGenerator {
    fun user() = User(
        userName = "${Constants.Account.USER_NAME_PREFIX}${System.currentTimeMillis()}",
        password = Constants.Account.PASSWORD
    )

    fun chatName() = "${Constants.Chat.OWNER_FULL_NAME} ${System.currentTimeMillis()}"
}
