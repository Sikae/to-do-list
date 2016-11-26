package com.sikaeapps.to_dolist;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemTest {

    @Test
    public void whenItemIsCreatedThenTitleIsSet() throws Exception {
        Item item = new Item("ToDoTitle");
        assertThat(item.getTitle(), is("ToDoTitle"));
    }

    @Test
    public void whenDescriptionIsGivenThenDescriptionIsSet() throws Exception {
        Item item = new Item("", "Description");
        assertThat(item.getDescription(), is("Description"));
    }

    @Test
    public void whenLocationIsGivenThenLocationIsSet() throws Exception {
        Item item = new Item("", "", "Location");
        assertThat(item.getLocation(), is("Location"));
    }
}
