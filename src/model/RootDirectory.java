package model;

import java.util.Date;

public class RootDirectory extends Directory {
    private static RootDirectory instance;

    private RootDirectory() {
        super("root", new Date());
    }

    public static synchronized RootDirectory getInstance() {
        if (instance == null) {
            instance = new RootDirectory();
        }
        return instance;
    }
}
