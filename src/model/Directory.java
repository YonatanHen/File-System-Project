package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class that represents a Directory in the system.
 */
public class Directory extends Item {
    private String name;
    private Date creationDate;
    private ArrayList<Item> items;

    /**
     * Constructs a new Directory object with specified name, date and an empty list of included items.
     *
     * @param name The name of the directory, up to 32 characters.
     * @param creationDate The date when the directory was created.
     */
    public Directory (String name, Date creationDate) {
        super(name, creationDate);
        this.items = new ArrayList<Item>();
    }

    public ArrayList<Item> getDictionaryItems() {
        return items;
    }
}
