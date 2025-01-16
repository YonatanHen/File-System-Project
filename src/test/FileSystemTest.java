package test;

import model.Directory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import service.FileSystem;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    private static FileSystem fs;

    @BeforeAll
    public static void FileSystemSetup() {
        fs = new FileSystem();

        fs.addFile("root", "1.txt", 20);
        fs.addFile("root", "2.txt", 500);
        fs.addDir("root", "home");
        fs.addFile("home", "3.txt", 90);
        fs.addFile("root", "5.txt", 499);
        fs.addDir("home", "photos");
    }

    @Test
    public void testAddDirectory() {
        assertDoesNotThrow(() -> fs.addDir("home", "documents"),
                "Adding a directory should not throw an exception if the parent directory exists");

        Directory dir = fs.FindDirectory("photos");
        assertNotNull(dir, "The directory 'documents' should exist in the file system");
    }

    @Test
    public void testParentDirectoryNotExists() {
        String parentDirName = "nonexistent";
        IllegalArgumentException exception = assertThrowsExactly(
                IllegalArgumentException.class,
                () -> fs.addDir(parentDirName, "music"),
                "Expected IllegalArgumentException to be thrown when parent directory does not exist"
        );

        assertEquals(
                exception.getMessage(),
                String.format("No such directory with the name of %s found in the system.", parentDirName)
        );
    }

    @Test
    public void testAddFileWithExistingName() {
        String fileName = "1.txt";
        IllegalArgumentException exception = assertThrowsExactly(
                IllegalArgumentException.class,
                () -> fs.addFile("home", fileName, 100),
                "Expected IllegalArgumentException to be thrown when a file with the same name already exists in the system."
        );

        assertEquals(
                exception.getMessage(),
                String.format("An item called " + fileName + " already exists in the system.", fileName)
        );
    }

    @Test
    public void testAddFile() {
        fs.addFile("home", "7.txt", 100);
        Directory dir = fs.FindDirectory("home");
        boolean file = dir.getDictionaryItems().stream()
                .anyMatch(item -> item.getName().equals("7.txt"));

        assertTrue(file, "File 7.txt should be found in the home directory");
    }

    @Test
    public void testGetBiggestFile() {
        assertNotEquals(fs.getBiggestFile(), "1.txt");
        assertEquals(fs.getBiggestFile(),"2.txt");
    }

    @Test
    public void testGetFileSize() {
        assertEquals(fs.getFileSize("2.txt"), 500);
    }

    @Test
    public void testDelete() {
        String itemName = "nonexistent";
        IllegalArgumentException exception = assertThrowsExactly(
                IllegalArgumentException.class,
                () -> fs.delete(itemName),
                "Expected IllegalArgumentException to be thrown when item does not exist"
        );

        assertEquals(
                exception.getMessage(),
                "No item with the name \"" + itemName + "\" found in the system."
        );
    }
}




















