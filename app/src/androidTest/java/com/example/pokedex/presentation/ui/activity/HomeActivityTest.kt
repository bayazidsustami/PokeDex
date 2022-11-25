package com.example.pokedex.presentation.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokedex.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun assertHeaderShouldShow() {
        onView(withContentDescription(R.string.poke_ball_icon)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.poke_dex_title)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_sort)).check(matches(isDisplayed()))
    }

    @Test
    fun inputSearchName(){
        onView(withId(R.id.et_search_field)).perform(click())
        onView(withId(R.id.et_search)).perform(typeText("bulb"))
        Thread.sleep(1000)
        onView(withId(R.id.btn_close)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun clickSortingButton() {
        onView(withId(R.id.btn_sort)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_sort)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_sort)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun scrollAndClickItem() {
        onView(withId(R.id.rv_list_poke)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(3000)
    }
}