package com.example.foroom.data

/** App languages used by the tests, with the profile labels expected in each of them. */
enum class AppLanguage(val changeLanguageLabel: String, val signOutLabel: String) {
    GEORGIAN(Constants.Georgian.CHANGE_LANGUAGE, Constants.Georgian.SIGN_OUT),
    ENGLISH(Constants.English.CHANGE_LANGUAGE, Constants.English.SIGN_OUT)
}
