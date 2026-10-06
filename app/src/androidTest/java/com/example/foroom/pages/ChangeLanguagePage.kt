package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.data.AppLanguage
import org.hamcrest.Matcher

/** Change language bottom sheet. */
object ChangeLanguagePage {
    val georgianButton = withId(R.id.languageButtonGeo)
    val englishButton = withId(R.id.languageButtonEng)

    fun languageButton(language: AppLanguage): Matcher<View> = when (language) {
        AppLanguage.GEORGIAN -> georgianButton
        AppLanguage.ENGLISH -> englishButton
    }
}
