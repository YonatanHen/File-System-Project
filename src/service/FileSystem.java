package service;

import model.Directory;
import model.File;
import model.Item;
import model.RootDirectory;

import java.util.LinkedList;
import java.util.Queue;

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

    /**
     * This function runs a BFS algorithm that finds a file/directory in the system.
     * If not such item exists, returns null.
     *
     * @param itemName The name of the searched item.
     * @return An Item object if exists, otherwise null.
     */
    public Item FindItem(String itemName) {
        Queue<Item> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            // Retrieve the item at the front of the Q and removes it.
            Item current = q.poll();

            if (current.getName().equals(itemName)) {
                return current;
            }

            if (current instanceof Directory dir) {
                // Enqueue all subdirectories and files of the current directory.
                q.addAll(dir.getDictionaryItems());
            }
        }

        return null;
    }
}
