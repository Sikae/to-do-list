package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class ToDoItemDetailsUITest {

    @Rule
    public ActivityTestRule<ToDoListActivity> toDoListActivityTestRule =
            new ActivityTestRule<>(ToDoListActivity.class);

    @Test
    public void whenUserClicksOnToDoItemThenItemDetailsAreShownInNewActivity() {
        Item item = toDoListActivityTestRule.getActivity().getManager().getItemAtIndex(0);
        onData(anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.item_title_details)).check(matches(withText(item.getTitle())));
        onView(withId(R.id.item_description_details)).check(matches(withText(item.getDescription())));
        onView(withId(R.id.item_location_details)).check(matches(withText(item.getLocation())));
        onView(withId(R.id.edit_button)).check(matches(withText(R.string.edit)));
    }


}
