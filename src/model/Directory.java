package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class that represents a Directory in the system.
 */
public class Directory extends Item {
    private ArrayList<Item> items;

    /**
     * Constructs a new Directory object with specified name, date and an empty list of included items.
     *
     * @param name The name of the directory, up to 32 characters.
     */
    public Directory (String name) {
        super(name);
        this.items = new ArrayList<Item>();
    }

    public ArrayList<Item> getDictionaryItems() {
        return items;
    }

    public void addItem(Item item) {
        if (item instanceof Directory dir) {
            this.items.add(dir);
        } else if (item instanceof File file) {
            this.items.add(file);
        }
    }
}
