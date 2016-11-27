package com.sikaeapps.to_dolist;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemManagerTest {

    private ItemManager manager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        manager = new ItemManager();
    }

    @Test
    public void whenItemManagerIsCreatedThenToDoItemsIsEmpty() throws Exception {
        assertThat(manager.getToDoItems().isEmpty(), is(true));
    }

    @Test
    public void whenItemManagerIsCreatedThenDoneItemsIsEmpty() throws Exception {
        assertThat(manager.getDoneItems().isEmpty(), is(true));
    }

    @Test
    public void whenItemManagerAddsAnItemThenToDoItemsSizeIsIncreased() throws Exception {
        manager.addItem(new Item(""));
        assertThat(manager.getToDoItems().size(), is(1));
    }

    @Test
    public void whenItemManagerChecksAToDoItemThenItemMovesFromToDoToDoneItems() throws Exception {
        manager.addItem(new Item(""));
        manager.checkItemAtIndex(0);
        assertThat(manager.getToDoItems().size(), is(0));
        assertThat(manager.getDoneItems().size(), is(1));
    }

    @Test
    public void givenItemWasAddedWhenGettingThatItemThenTheItemIsReturned() throws Exception {
        Item item = new Item("Title", "Description", "Location");
        manager.addItem(item);
        Item returnedItem = manager.getItemAtIndex(0);
        assertThat(returnedItem, is(item));
    }

    @Test
    public void whenAllItemsAreRemovedThenToDoItemsAndDoneItemsAreEmpty() throws Exception {
        manager.addItem(new Item(""));
        manager.addItem(new Item("Title"));
        manager.checkItemAtIndex(0);
        manager.removeAll();
        assertThat(manager.getToDoItems().isEmpty(), is(true));
        assertThat(manager.getDoneItems().isEmpty(), is(true));
    }

    @Test
    public void whenItemAlreadyExistsAnExceptionIsThrown() throws Exception {
        manager.addItem(new Item(""));
        exception.expect(DuplicatedItemException.class);
        manager.addItem(new Item(""));
    }

    @Test
    public void whenItemIsUpdatedThenUpdatedItemIsInToDoItems() throws DuplicatedItemException {
        manager.addItem(new Item(""));
        Item updatedItem = new Item("Title", "Description", "Location");
        manager.updateItemAt(0, updatedItem);
        assertThat(manager.getItemAtIndex(0), is(updatedItem));
    }

    @Test
    public void whenUpdatedItemIsAlreadyInToDoItemsAnExceptionIsThrown() throws DuplicatedItemException {
        Item itemOne = new Item("Title1", "Description1", "Location1");
        Item itemTwo = new Item("Title2", "Description2", "Location2");
        manager.addItem(itemOne);
        manager.addItem(itemTwo);
        exception.expect(DuplicatedItemException.class);
        manager.updateItemAt(0, itemTwo);
    }

}
