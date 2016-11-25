package com.sikaeapps.to_dolist;

public class Item {
    private String title;
    private String description;
    private String location;

    public Item(String title, String description, String location) {
        this(title, description);
        this.location = location;
    }

    public Item(String title, String description) {
        this(title);
        this.description = description;
    }

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null)
            return false;
        return location != null ? location.equals(item.location) : item.location == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
