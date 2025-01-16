package test;

import model.Directory;
import model.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {
    @Test
    public void testAddFile() {
        Directory dir = new Directory("root");
        dir.addItem(new File("file1.txt", 100));
        assertEquals(1, dir.getDictionaryItems().size());
    }

    @Test
    public void testAddSubDirectory() {
        Directory dir = new Directory("root");
        Directory subDir = new Directory("sub");
        dir.addItem(subDir);
        assertEquals(1, dir.getDictionaryItems().size());
    }
}

