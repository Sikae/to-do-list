package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
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
        onView(withId(R.id.title_edit)).check(matches(withText(item.getTitle())));
        onView(withId(R.id.title_edit)).check(matches(withHint("Title")));
        onView(withId(R.id.description_edit)).check(matches(withText(item.getDescription())));
        onView(withId(R.id.description_edit)).check(matches(withHint("Description")));
        onView(withId(R.id.location_edit)).check(matches(withText(item.getLocation())));
        onView(withId(R.id.location_edit)).check(matches(withHint("Location")));
        onView(withId(R.id.save_edit)).check(matches(withText("Save")));
    }
}
