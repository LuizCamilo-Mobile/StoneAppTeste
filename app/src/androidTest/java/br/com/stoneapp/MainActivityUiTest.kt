// FILE: app/src/androidTest/java/br/com/stoneapp/MainActivityUiEspressoTest.kt
package br.com.stoneapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import br.com.stoneapp.ui.MainActivity
import org.junit.Test

class MainActivityUiEspressoTest {

    @Test
    fun appLaunches_andComposeViewIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.fragmentContainer)).check(matches(isDisplayed()))
            onView(withId(br.com.stoneapp.feature.home.R.id.composeView)).check(matches(isDisplayed()))
        }
    }
}
