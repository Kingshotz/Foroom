package com.example.foroom.data

object Constants {
    const val TRAINING_PACKAGE = "com.alternator.foroom.training"

    const val WAIT_TIMEOUT_SEC = 10L

    object Account {
        const val USER_NAME_PREFIX = "shota"
        const val PASSWORD = "pass123"
        const val NEW_PASSWORD = "newpass123"
        const val AVATAR_ID = 1
    }

    object Chat {
        const val OWNER_FULL_NAME = "Shota Shalamberidze"

        const val IMAGE_INDEX = 1
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
