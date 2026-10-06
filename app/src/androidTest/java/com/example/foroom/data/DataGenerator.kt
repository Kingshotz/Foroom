package com.example.foroom.data

/** Generates test data that must be different on every run. */
object DataGenerator {

    /** Chat name made of the student's full name and a timestamp, so each run finds only its own chat. */
    fun chatName() = "${Constants.Chat.OWNER_FULL_NAME} ${System.currentTimeMillis()}"
}
