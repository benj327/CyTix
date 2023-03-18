package com.cytix;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
class CreateUsersEspressoTest {

    @Rule
    public ActivityScenarioRule<Login> activityRule =
            new ActivityScenarioRule<>(Login.class);

    @Test
    public void CreateBaseUsers() {
        onView(withId(R.id.login_createAccount)).perform(click());
        onView(withId(R.id.createAccount_userName2)).perform(typeText("user1"));
        onView(withId(R.id.createAccount_userEmail)).perform(typeText("user1"));
        onView(withId(R.id.createAccount_userPassword2)).perform(typeText("1"));
        onView(withId(R.id.accSelector)).perform(click());
        onData(withText("User")).perform(click());
    }
}