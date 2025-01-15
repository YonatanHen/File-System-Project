package model;

import java.util.Date;

public class File extends Item {
    private String name;
    private long size;
    private Date creationDate;

    public File (String name, long size, Date creationDate) {
        super(name, creationDate);
        this.size = size;
    }
}
