package com.droidrank.checklist;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public void checkButtonClick(){
        Espresso.onView(ViewMatchers.withId(R.id.bt_add_new_item)).perform(click());
    }

    @Test
    public void activityLaunch() {
        checkButtonClick();
        Espresso.onView(ViewMatchers.withId(R.id.et_item_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.et_item_name))
                .perform(typeText("Espresso"));
        Espresso.onView(ViewMatchers.withId(R.id.bt_ok)).perform(click());
    }


}
