package com.chetan_pawar.obvious_assignment

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.chetan_pawar.obvious_assignment.ui.main.ImagesAdapter
import com.chetan_pawar.obvious_assignment.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isRecyclerView_visible() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailActivityVisible() {
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<ImagesAdapter.ImageViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.image_copyright_data)).check(matches(withText("ESA/HubbleNASA")))
    }


    @Test
    fun test_backNavigation_toMainActivity() {
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<ImagesAdapter.ImageViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.image_copyright_data)).check(matches(withText("ESA/HubbleNASA")))


        Espresso.pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }
}