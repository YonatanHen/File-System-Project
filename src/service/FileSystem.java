package service;

import model.Directory;
import model.File;
import model.Item;
import model.RootDirectory;

import java.util.*;

/**
 * The file system service which includes the implementation of the required function and initialize the root folder.
 */
public class FileSystem {
    private RootDirectory root;
    private Set<String> itemsNames;

    /**
     * Constructs a File System with the root directory.
     */
    public FileSystem() {
        this.root = RootDirectory.getInstance();
        this.itemsNames = new HashSet<>();
    }

    /**
     * Utility function that runs a BFS algorithm which finds a directory in the system.
     * If no such directory exists, returns null.
     *
     * Time complexity: O(V+E) where V is the number of directories and E is the number of items (both files or empty directories).
     * Space complexity: O(N) where N is the number of the items in the system.
     *
     * @param dirName The name of the searched directory.
     * @return Directory object if exists, otherwise null.
     */
    public Directory FindDirectory(String dirName) {
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

    /**
     * Utility function that check if an item with the same name already exists.
     *
     * Time complexity: O(1) constant time for finding a value in a hash set.
     * Space complexity: O(1)
     *
     * @param itemName The name of the item that needs to be checked.
     */
    private void checkIfItemExists(String itemName) {
        if (this.itemsNames.contains(itemName)) {
            throw new IllegalArgumentException("An item called " + itemName + " already exists in the system.");
        }
    }

    /**
     * Runs a BFS algorithm to find the parent directory
     * and eventually adds a new file with its parameters to this directory.
     *
     * Time complexity: O(V+E), where V is the number of directories and E is the total number of items in the file system.
     * Space complexity: O(N), where N is the maximum number of items at any level of the hierarchy.
     *
     * @param parentDirName The name of the directory where the file should be added.
     * @param fileName The name of the file to be added.
     * @param fileSize The size of the file in bytes.
     * @throws IllegalArgumentException If the parent directory is not found.
     */
    public void addFile(String parentDirName, String fileName, int fileSize) {
        checkIfItemExists(fileName);
        Directory parentDir = FindDirectory(parentDirName);
        if (parentDir != null) {
            parentDir.addItem(new File(fileName, fileSize));
            itemsNames.add(fileName);
        } else {
            throw new IllegalArgumentException(String.format("No such directory with the name of %s found in the system.", parentDirName));
        }
    }

    /**
     * Runs a BFS algorithm to find the parent directory
     * and adds a new directory to this parent directory.
     *
     * Time complexity: O(V+E), where V is the number of directories and E is the total number of items in the file system.
     * Space complexity: O(N), where N is the maximum number of items at any level of the hierarchy.
     *
     * @param parentDirName The name of the directory where the new directory should be added.
     * @param dirName The name of the directory to be added.
     * @throws IllegalArgumentException If the parent directory is not found.
     */
    public void addDir(String parentDirName, String dirName) {
        checkIfItemExists(dirName);
        Directory parentDir = FindDirectory(parentDirName);
        if (parentDir != null) {
            parentDir.addItem(new Directory(dirName));
            itemsNames.add(dirName);
        } else {
            throw new IllegalArgumentException(String.format("No such directory with the name of %s found in the system.", parentDirName));
        }
    }

    /**
     * A BFS based finding algorithm that find the file in the system based on the file name
     * and return its size.
     *
     * Time complexity: O(V+E), where V is the number of directories and E is the total number of items in the file system.
     * Space complexity: O(N), where N is the maximum number of items at any level of the hierarchy.
     *
     * @param fileName The name of the file whose size is to be retrieved.
     * @return The size of the file in bytes, or -1 if the file is not found.
     */
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

    /**
     * A BFS based finding algorithm that returns the size of the largest file in the file system.
     *
     * Time complexity: O(V+E), where V is the number of directories and E is the total number of items in the file system.
     * Space complexity: O(N), where N is the maximum number of items at any level of the hierarchy.
     *
     * @return The size of the largest file in bytes, or -1 if there are no files in the system.
     */
    public String getBiggestFile() {
        Queue<Item> q = new LinkedList<>();
        q.add(this.root);

        // File size must be a positive number.
        long maxSize = -1;
        String fileName = "";

        while(!q.isEmpty()) {
            // Retrieve the item at the front of the Q and removes it.
            Item current = q.poll();

            if (current instanceof File file && file.getSize() > maxSize) {
                maxSize = file.getSize();
                fileName = file.getName();
            } else if (current instanceof Directory dir) {
                q.addAll(dir.getDictionaryItems());
            }
        }

        return fileName;
    }

    /**
     * Displays all files & directories with their hierarchical structure (a file should display all
     * file properties and a directory should display all directory properties).
     *
     * Time complexity: O(N) similarly to the toString() function in the Directory class.
     * Space complexity: O(D+N) similarly to the toString() function in the Directory class.
     */
    public void showFileSystem() {
        System.out.println(root.toString());
    }

    /**
     * Finds the Directory or the File in the system (BFS based) and deletes it.
     *
     * Time complexity: O(V+E), where V is the number of directories and E is the total number of items in the file system.
     * Space complexity: O(N), where N is the maximum number of items at any level of the hierarchy.
     *
     * @param name The name of the item that should be deleted.
     * @throws IllegalArgumentException if root folder provided or the provided name not found in the system.
     */
    public void delete(String name) {
        if (name.equals("root")) {
            throw new IllegalArgumentException("root folder can not be deleted!");
        }

        Queue<Item> q = new LinkedList<>();
        q.add(this.root);

        while(!q.isEmpty()) {
            Item current = q.poll();

            if (current instanceof Directory dir) {
                ArrayList<Item> items = dir.getDictionaryItems();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getName().equals(name)) {
                        items.remove(i);
                        itemsNames.remove(name);
                        System.out.println("Item \"" + name + "\" has been deleted.");
                        return;
                    }
                }
            }
        }

        throw new IllegalArgumentException("No item with the name \"" + name + "\" found in the system.");
    }
}
