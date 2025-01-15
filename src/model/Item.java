package model;

import java.util.Date;

/**
 * An abstract class for an item in the file system, contains mutual fields.
 */
public abstract class Item {
    private String name;
    private Date creationDate;

    /**
     * The Item class constructor
     *
     * @param name The name of the item, up to 32 characters.
     */
    public Item(String name, Date creationDate) {
        if (name.length() > 32) {
            throw new IllegalArgumentException("Name can't contain more than 32 characters!");
        }

        this.name = name;
        this.creationDate = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
