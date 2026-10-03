package com.example.foroom.data

/** Generates test data that must be different on every run. */
object DataGenerator {

    /** A username that has never been registered. */
    fun unknownUserName() = uniqueUserName(Constants.UNKNOWN_USER_NAME_PREFIX)

    /** A free username for registering a new account. */
    fun newUserName() = uniqueUserName(Constants.NEW_USER_NAME_PREFIX)

    private fun uniqueUserName(prefix: String) = "$prefix${System.currentTimeMillis()}"
}
