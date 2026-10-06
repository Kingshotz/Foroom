package com.example.foroom.data

/**
 * Every fixed value used by the tests: timeouts, account data and expected texts.
 * Texts are copied from the app's string resources (values and values-ka).
 */
object Constants {
    const val TRAINING_PACKAGE = "com.alternator.foroom.training"

    // Maximum time to wait for the app's asynchronous updates (login, language change, chat loading)
    const val WAIT_TIMEOUT_SEC = 10L

    /** Dedicated test account of the Foroom Training app. It is reset before every test. */
    object Account {
        const val USER_NAME = "shota"
        const val PASSWORD = "pass123"
        const val NEW_PASSWORD = "newpass123"
        const val AVATAR_ID = 1
    }

    object Chat {
        const val OWNER_FULL_NAME = "Shota Shalamberidze"

        // Index of the chat image to select; the first one is selected by default
        const val IMAGE_INDEX = 1
    }

    object LoginErrors {
        const val USER_NAME_DOES_NOT_EXIST = "Username does not exist"
        const val INCORRECT_PASSWORD = "Incorrect password"
    }

    /** Profile labels shown when the app language is Georgian. */
    object Georgian {
        const val CHANGE_LANGUAGE = "ენის შეცვლა"
        const val SIGN_OUT = "გამოსვლა"
    }

    /** Profile labels shown when the app language is English. */
    object English {
        const val CHANGE_LANGUAGE = "Change Language"
        const val SIGN_OUT = "Sign Out"
    }
}
