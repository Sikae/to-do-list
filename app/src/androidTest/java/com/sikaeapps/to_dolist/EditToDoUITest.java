package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class EditToDoUITest {

    @Rule
    public ActivityTestRule<ToDoListActivity> toDoListActivityTestRule =
            new ActivityTestRule<>(ToDoListActivity.class);

    @Test
    public void givenUserHasSelectedAnItemWhenHePressesEditButtonANewActivityIsLaunched() {
        Item item = toDoListActivityTestRule.getActivity().getManager().getItemAtIndex(0);
        onData(anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.edit_button)).perform(click());
        onView(withId(R.id.new_edit_item_title)).check(matches(withText(item.getTitle())));
        onView(withId(R.id.new_edit_item_title)).check(matches(withHint(R.string.title_hint)));
        onView(withId(R.id.new_edit_item_description)).check(matches(withText(item.getDescription())));
        onView(withId(R.id.new_edit_item_description)).check(matches(withHint(R.string.description_hint)));
        onView(withId(R.id.new_edit_item_location)).check(matches(withText(item.getLocation())));
        onView(withId(R.id.new_edit_item_location)).check(matches(withHint(R.string.location_hint)));
        onView(withId(R.id.save_button)).check(matches(withText(R.string.save)));
    }
}
