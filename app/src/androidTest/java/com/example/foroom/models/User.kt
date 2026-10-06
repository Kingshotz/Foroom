package com.example.foroom.models

/** A test user. The password is mutable because a test can change it in the app. */
data class User(val userName: String, var password: String)
