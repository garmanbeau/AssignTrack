/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se470.assigntrack2;

import net.fortuna.ical4j.model.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author garma
 */
public class IcsCalendarManagerTest {
    
    public IcsCalendarManagerTest() {
    }
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test(expected = Exception.class)
    public void testGenerateAndSaveIcsFilePath1() throws IOException {
        IcsCalendarManager icm = new IcsCalendarManager();
        File tempFile = folder.newFile("tempFile.ics");
        icm.generateAndSaveIcsFile(tempFile.getAbsolutePath());
    }

    @Test
    public void testGenerateAndSaveIcsFilePath2() {
        IcsCalendarManager icm = new IcsCalendarManager();
        icm.generateAndSaveIcsFile("/path/to/nonexistent/directory/tempFile.ics");
    }
}
    

