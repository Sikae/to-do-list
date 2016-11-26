package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class AddNewItemUITest {

    @Rule
    public ActivityTestRule<ToDoListActivity> toDoListActivityTestRule =
            new ActivityTestRule<>(ToDoListActivity.class);

    @Test
    public void whenAddNewItemIsPressedUIElementsAreShown() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.new_item_title)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_title)).check(matches(withHint("Title")));
        onView(withId(R.id.new_item_description)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_description)).check(matches(withHint("Description...")));
        onView(withId(R.id.new_item_location)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_location)).check(matches(withHint("Location")));
        onView(withId(R.id.new_item_save_button)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_save_button)).check(matches(withText("Save")));
    }
}
