package model;

import java.util.ArrayList;
import java.util.Date;

public class Directory extends Item {
    private String name;
    private Date creationDate;
    private ArrayList<Item> items;

    public Directory (String name, Date creationDate) {
        super(name, creationDate);
        this.items = new ArrayList<Item>();
    }

    public ArrayList<Item> getDictionaryItems() {
        return items;
    }
}
