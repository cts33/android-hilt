/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hilt

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.hilt.data.LoggerInMemoryDataSource
import com.example.android.hilt.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val TAG = "AppTest"

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    //    @After
//    fun tearDown() {
//        // Remove logs after the test finishes
//        ServiceLocator(getInstrumentation().targetContext).loggerLocalDataSource.removeLogs()
//    }
    @Test
    fun testAddData() {
        val logger = LoggerInMemoryDataSource()
        logger.addLog("log.hashcode=${logger.hashCode()}")
    }

    @Test
    fun testGetAllData() {
        val logger = LoggerInMemoryDataSource()
        logger.addLog("log.hashcode=${logger.hashCode()}")
        logger.getAllLogs {
            for (item in it) {
                Log.d(TAG, "testGetAllData: ")
                println("testGetAllData: $item")
            }
        }
//       logger.removeLogs()
    }

    @Test
    fun happyPath() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        onView(withId(R.id.textView)).check(matches(isDisplayed()))

        // Tap on Button 1
        onView(withId(R.id.button1)).perform(click())

        // Navigate to Logs screen
        onView(withId(R.id.all_logs)).perform(click())

        // Check Logs fragment screen is displayed
        onView(withText(containsString("Interaction with 'Button 1'")))
            .check(matches(isDisplayed()))
    }
}
