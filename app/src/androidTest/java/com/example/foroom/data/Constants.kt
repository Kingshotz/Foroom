package com.example.foroom.data

/**
 * Test data for the training app (com.alternator.foroom.training).
 * Accounts and error texts come from its local backend (TrainingInterceptor).
 */
object Constants {
    const val TRAINING_PACKAGE = "com.alternator.foroom.training"

    // Demo account that the training app creates automatically
    const val EXISTING_USER_NAME = "student"
    const val WRONG_PASSWORD = "WrongPassword1"
    const val NEW_PASSWORD = "Test12345"

    // Prefixes for generated usernames; a timestamp is added so every run uses a new name
    const val UNKNOWN_USER_NAME_PREFIX = "nouser"
    const val NEW_USER_NAME_PREFIX = "user"

    // The first avatar is selected by default, so the test picks the second one
    const val AVATAR_INDEX = 1

    const val USER_NAME_ERROR = "Username does not exist"
    const val PASSWORD_ERROR = "Incorrect password"
}
