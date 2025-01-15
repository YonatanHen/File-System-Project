package service;

import model.RootDirectory;

/**
 * The file system service which includes the implementation of the requirements.
 */
public class FileSystem {
    private RootDirectory root;

    /**
     * Constructs a File System with the root directory.
     */
    public FileSystem() {
        root = RootDirectory.getInstance();
    }

    public void addFile(String parentDirName, String fileName, int fileSize) {

    }

    public void addDir(String parentDirName, String dirName) {

    }

    public void getFileSize(String fileName) {

    }

    public void getBiggestFile() {

    }

    public void showFileSystem() {

    }

    public void delete(String name) {

    }
}
