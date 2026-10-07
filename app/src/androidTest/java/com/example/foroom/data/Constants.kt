package com.example.foroom.data

object Constants {
    const val TRAINING_PACKAGE = "com.alternator.foroom.training"

    const val WAIT_TIMEOUT_SEC = 10L

    object Account {
        const val USER_A_PREFIX = "shotaA"
        const val USER_B_PREFIX = "shotaB"
        const val PASSWORD = "pass123"
        const val AVATAR_ID = 1
    }

    object Chat {
        const val OWNER_FULL_NAME = "Shota Shalamberidze"
        const val JOHN_WEEK = "johnWeek"
        const val SHARED_CHAT_PREFIX = "something"
        const val EMOJI_ID = 1
        const val IMAGE_INDEX = 1
    }

    object Messages {
        const val DRINK = "let's go for a drink"
        const val QUESTION = "Which module do you like most in the Automation Academy?"
        const val GREETING = "Hello from User A"
        const val ADDITIONAL = "Message"
        const val ADDITIONAL_COUNT = 25
        const val REPLY = "Hi User A, this is User B"
    }

    object Swipe {
        const val SWIPER_X = 500
        const val DURATION_MS = 500
        const val MAX_ATTEMPTS = 10
        const val EDGE_OFFSET_RATIO = 0.1
    }

    object LoginErrors {
        const val USER_NAME_DOES_NOT_EXIST = "Username does not exist"
        const val INCORRECT_PASSWORD = "Incorrect password"
    }

    object Georgian {
        const val CHANGE_LANGUAGE = "ენის შეცვლა"
        const val SIGN_OUT = "გამოსვლა"
    }

    object English {
        const val CHANGE_LANGUAGE = "Change Language"
        const val SIGN_OUT = "Sign Out"
    }
}
