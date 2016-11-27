package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

public class AddNewItemUITest {

    @Rule
    public ActivityTestRule<ToDoListActivity> toDoListActivityTestRule =
            new ActivityTestRule<>(ToDoListActivity.class);

    @Before
    public void setUp() throws Exception {
        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void whenAddNewItemIsPressedUIElementsAreShown() {
        onView(withId(R.id.new_item_title)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_title)).check(matches(withHint("Title")));
        onView(withId(R.id.new_item_description)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_description)).check(matches(withHint("Description...")));
        onView(withId(R.id.new_item_location)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_location)).check(matches(withHint("Location")));
        onView(withId(R.id.new_item_save_button)).check(matches(isDisplayed()));
        onView(withId(R.id.new_item_save_button)).check(matches(withText("Save")));
    }

    @Test
    public void whenUserSavesANewItemThenItemIsDisplayedOnToDoItemList() throws Exception {
        onView(withId(R.id.new_item_title)).perform(typeText("Do the laundry"));
        onView(withId(R.id.new_item_description)).perform(typeText("Do the laundry at home at 9.00 am"));
        onView(withId(R.id.new_item_location)).perform(typeText("Home"));

        onView(withId(R.id.new_item_save_button)).perform(click());


        onData(anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(1).
                onChildView(withId(R.id.item_title)).
                check(matches(withText("Do the laundry")));

        onData(anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(1).
                onChildView(withId(R.id.item_location)).
                check(matches(withText("Home")));
    }
}
