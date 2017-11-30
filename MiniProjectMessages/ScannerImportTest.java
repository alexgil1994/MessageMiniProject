// TODO These methods can't be tested because the scanner inside the scannerImport methods waiting for an input.

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScannerImportTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getReadActivity() {
        ScannerImport scannerImport = new ScannerImport();
        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerImport.readActivity();

        assertEquals(input , scannerImport.getReadActivity(),"getReadActivity method in ScannerImport could not run correctly.");
    }

    @Test
    void getNewMessage() {
        ScannerImport scannerImport = new ScannerImport();
        String input = "Hello there";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerImport.readActivity();

        assertEquals(input , scannerImport.getNewMessage(),"getNewMessage method in ScannerImport could not run correctly.");
    }

    @Test
    void readActivity() {
    }

    @Test
    void readNewMessage() {
        ScannerImport scannerImport = new ScannerImport();
        String input = "Hello this is a new message to test.";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerImport.readActivity();

        assertEquals(input , scannerImport.getNewMessage(),"readNewMessage method in ScannerImport could not run correctly.");
    }

    @Test
    void readNumOfMessages() {
    }

}