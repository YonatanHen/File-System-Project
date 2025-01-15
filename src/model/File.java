package model;

import java.util.Date;

public class File extends Item {
    private long size;

    /**
     * Constructs a new File object with specified name, date and size.
     *
     * @param name The name of the file, up to 32 characters.
     * @param size The size of the file in bytes, must be a positive long.
     */
    public File (String name, long size, Date creationDate) {
        super(name, creationDate);
        this.size = size;
    }

    public long getSize() {
        return size;
    }
}
