package com.sikaeapps.to_dolist;

import android.support.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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

    @Test
    public void givenUserEditsAToDoItemWhenPressingSaveItemIsUpdatedOnToDoItemList() {
        onView(withId(R.id.fab)).perform(click());
        entryTask("Title", "Description...", "Location");
        onView(withId(R.id.save_button)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(1).perform(click());
        onView(withId(R.id.edit_button)).perform(click());

        replaceTask("Do the laundry", "Do the laundry at home at 9.00 am", "Home");
        onView(withId(R.id.save_button)).perform(click());

        onData(CoreMatchers.anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(1).
                onChildView(withId(R.id.item_title)).
                check(matches(withText("Do the laundry")));

        onData(CoreMatchers.anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(1).
                onChildView(withId(R.id.item_location)).
                check(matches(withText("Home")));
    }

    @Test
    public void whenUserIsEditingAndTheItemEditedIsAlreadyInToDoListThenAToastMessageIsDisplayed() {
        onView(withId(R.id.fab)).perform(click());
        entryTask("Title1", "Description...1", "Location1");
        onView(withId(R.id.save_button)).perform(click());

        onView(withId(R.id.fab)).perform(click());
        entryTask("Title2", "Description...2", "Location2");
        onView(withId(R.id.save_button)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.to_do_list_view)).atPosition(1).perform(click());
        onView(withId(R.id.edit_button)).perform(click());

        replaceTask("Title2", "Description...2", "Location2");
        onView(withId(R.id.save_button)).perform(click());

        onView(withText(R.string.duplicated_item_message)).
                inRoot(withDecorView(not(is(toDoListActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    private void entryTask(String title, String description, String location) {
        onView(withId(R.id.new_edit_item_title)).perform(typeText(title));
        onView(withId(R.id.new_edit_item_description)).perform(typeText(description));
        onView(withId(R.id.new_edit_item_location)).perform(typeText(location));
    }

    private void replaceTask(String title, String description, String location) {
        onView(withId(R.id.new_edit_item_title)).perform(replaceText(title));
        onView(withId(R.id.new_edit_item_description)).perform(replaceText(description));
        onView(withId(R.id.new_edit_item_location)).perform(replaceText(location));
    }
}
