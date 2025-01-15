package model;

import java.util.Date;

public abstract class Item {
    private String name;
    private Date creationDate;

    public Item(String name, Date creationDate) {
        if (name.length() > 32) {
            throw new IllegalArgumentException("Name can't contain more than 32 characters!");
        }

        this.name = name;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
