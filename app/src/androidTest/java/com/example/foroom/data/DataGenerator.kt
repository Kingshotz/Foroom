package com.example.foroom.data

import com.example.foroom.models.User

object DataGenerator {
    fun user(namePrefix: String) = User(
        userName = "$namePrefix${uniqueSuffix()}",
        password = Constants.Account.PASSWORD
    )

    fun chatName() = "${Constants.Chat.OWNER_FULL_NAME} ${uniqueSuffix()}"

    fun sharedChatName() = "${Constants.Chat.SHARED_CHAT_PREFIX} ${uniqueSuffix()}"

    fun withUniqueSuffix(text: String) = "$text ${uniqueSuffix()}"

    fun additionalMessages(suffix: String) = (1..Constants.Messages.ADDITIONAL_COUNT).map { number ->
        "${Constants.Messages.ADDITIONAL} $number $suffix"
    }

    fun uniqueSuffix() = System.currentTimeMillis().toString()
}
