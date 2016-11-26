package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ToDoListUITest {

    @Rule
    public ActivityTestRule<ToDoListActivity> toDoListActivityTestRule =
            new ActivityTestRule<>(ToDoListActivity.class);

    @Test
    public void whenUserLaunchesAppUIElementsAreShown() throws Exception {
        onView(withId(R.id.to_do_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.to_do_text_view)).check(matches(withText(R.string.todo)));
        onView(withId(R.id.to_do_list_view)).check(matches(isDisplayed()));
        onView(withId(R.id.done_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.done_text_view)).check(matches(withText(R.string.done)));
        onView(withId(R.id.done_list_view)).check(matches(isDisplayed()));
    }
}
