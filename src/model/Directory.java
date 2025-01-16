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

    /**
     * Time Complexity: O(N) where N is the number of nested items.
     * Space complexity: O(D+N) where N is the number of nested items, and D is the depth of the directory hierarchy.
     *
     * @param indent The level of indentation.
     * @return A string representation of current Directory and its subdirectories (if exist).
     */
    @Override
    public String toString(int indent) {
        String indentation = "  ".repeat(indent); // Indentation based on depth
        StringBuilder result = new StringBuilder(indentation + "Directory | " + getName() + " | " + getCreationDate());

        // Recursively add the items in the directory with increased indentation
        for (Item item : items) {
            result.append("\n").append(item.toString(indent + 1)); // Proper indentation and line breaks
        }

        return result.toString();
    }
}
