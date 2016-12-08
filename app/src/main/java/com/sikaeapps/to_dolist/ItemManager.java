package com.sikaeapps.to_dolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemManager implements Serializable {

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
        return toDoItems.get(index);
    }

    public void removeAll() {
        toDoItems.clear();
        doneItems.clear();
    }

    public void updateItemAt(int index, Item item) throws DuplicatedItemException {
        for (int i = 1; i < toDoItems.size(); i++) {
            if (toDoItems.get(i).equals(item)) {
                throw new DuplicatedItemException("Item already exists.");
            }
        }

        toDoItems.set(index, item);
    }

    public void removeItemAt(int index) {
        toDoItems.remove(index);
    }
}
