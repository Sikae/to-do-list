package com.sikaeapps.to_dolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemManager implements Serializable{

    private List<Item> toDoItems;
    private List<Item> doneItems;

    public ItemManager() {
        toDoItems = new ArrayList<>();
        doneItems = new ArrayList<>();
    }

    public List<Item> getToDoItems() {
        return toDoItems;
    }

    public List<Item> getDoneItems() {
        return doneItems;
    }

    public void addItem(Item item) throws DuplicatedItemException {
        if (toDoItems.contains(item)) {
            throw new DuplicatedItemException("Item already exists.");
        }
        toDoItems.add(item);
    }

    public void checkItemAtIndex(int index) {
        doneItems.add(toDoItems.remove(index));
    }

    public Item getItemAtIndex(int index) {
        return new Item("Title", "Description", "Location");
    }

    public void removeAll() {
        toDoItems.clear();
        doneItems.clear();
    }
}
