package model;

import java.util.Date;

/**
 * An implementation of the "root" directory singleton.
 * We assume that we must have this directory similarly to real OS and can not create the
 * rest of the folders without this Directory instance
 */
public class RootDirectory extends Directory {
    private static RootDirectory instance;

    /**
     * Constructs the "root" directory instance.
     */
    private RootDirectory() {
        super("root", new Date());
    }

    /**
     * This function returns a new RootDirectory if no instance exists, else returns the current instance.
     *
     * @return RootDirectory instance.
     */
    public static synchronized RootDirectory getInstance() {
        if (instance == null) {
            instance = new RootDirectory();
        }
        return instance;
    }
}
