package test;

import model.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    @Test
    public void testFileCreation() {
        File file = new File("test.txt", 100);
        assertEquals("test.txt", file.getName());
        assertEquals(100, file.getSize());
    }
}
