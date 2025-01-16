package service;

import model.Directory;
import model.File;
import model.Item;
import model.RootDirectory;

import java.util.ArrayList;
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
        this.root = RootDirectory.getInstance();
    }

    /**
     * Utility function that run a BFS algorithm which finds a directory in the system.
     * If not such directory exists, returns null.
     *
     * Time complexity: O(V+E) where V is the number of directories (vertex) and E is the number of items (both files or empty directories, i.e. edges).
     * Space complexity: O(N) where N is the number of the items in the system.
     *
     * @param dirName The name of the searched directory.
     * @return Directory object if exists, otherwise null.
     */
    private Directory FindDirectory(String dirName) {
        Queue<Item> q = new LinkedList<>();
        q.add(this.root);

        while(!q.isEmpty()) {
            // Retrieve the item at the front of the Q and removes it.
            Item current = q.poll();

            if (current instanceof Directory dir) {
                if (dir.getName().equals(dirName)) {
                    return dir;
                }
                q.addAll(dir.getDictionaryItems());
            }
        }

        return null;
    }

    public void addFile(String parentDirName, String fileName, int fileSize) {
        Directory parentDir = FindDirectory(parentDirName);
        if (parentDir != null) {
            parentDir.addItem(new File(fileName, fileSize));
        } else {
            throw new IllegalArgumentException(String.format("No such directory with the name of %s found in the system.", parentDirName));
        }
    }

    public void addDir(String parentDirName, String dirName) {
        Directory parentDir = FindDirectory(parentDirName);
        if (parentDir != null) {
            parentDir.addItem(new Directory(dirName));
        } else {
            throw new IllegalArgumentException(String.format("No such directory with the name of %s found in the system.", parentDirName));
        }
    }

    public long getFileSize(String fileName) {
        Queue<Item> q = new LinkedList<>();
        q.add(this.root);

        while(!q.isEmpty()) {
            // Retrieve the item at the front of the Q and removes it.
            Item current = q.poll();

            if (current instanceof File file && current.getName().equals(fileName)) {
                return file.getSize();
            } else if (current instanceof Directory dir) {
                q.addAll(dir.getDictionaryItems());
            }
        }

        return -1;
    }

    public long getBiggestFile() {
        Queue<Item> q = new LinkedList<>();
        q.add(this.root);

        // File size must be a positive number.
        long maxSize = -1;

        while(!q.isEmpty()) {
            // Retrieve the item at the front of the Q and removes it.
            Item current = q.poll();

            if (current instanceof File file && file.getSize() > maxSize) {
                maxSize = file.getSize();
            } else if (current instanceof Directory dir) {
                q.addAll(dir.getDictionaryItems());
            }
        }

        return maxSize;
    }

    /**
     * Displays all files & directories with their hierarchical structure (a file should display all
     * file properties and a directory should display all directory properties)
     *
     * Time complexity: O(N) similarly to the toString() function in the Directory class.
     * Space complexity: O(D+N) similarly to the toString() function in the Directory class.
     */
    public void showFileSystem() {
        System.out.println(root.toString());
    }

    /**
     * Deletes the Directory or the File with this name.
     *
     * @param name The name of the item that should be deleted.
     */
    public void delete(String name) {
        Queue<Item> q = new LinkedList<>();
        q.add(this.root);

        while(!q.isEmpty()) {
            Item current = q.poll();

            if (current instanceof Directory dir) {
                ArrayList<Item> items = dir.getDictionaryItems();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getName().equals(name)) {
                        items.remove(i);
                        System.out.println("Item \"" + name + "\" has been deleted.");
                        return;
                    }
                }
            }
        }

        throw new IllegalArgumentException("No item with the name \"" + name + "\" found in the system.");
    }
}
