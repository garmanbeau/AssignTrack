/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se470.assigntrack2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author garma
 */
public class AssignTrack2Test {
    
    @Test
    public void testGenerateICSFile_Path1() {
        // Create a mock IcsCalendarManager
        AssignTrack2 at2 = new AssignTrack2();


        // Define the JSON data
        String data = "[{\"Title\": \"Test Event 1\", \"Class\": \"Test Class\", \"Due Date\": \"2022-12-31T23:59:59\"}, {\"Title\": \"Test Event 2\", \"Class\": \"Test Class\", \"Due Date\": \"2022-12-31T23:59:59\"}]";
        String path = "path/to/file.ics";

        // Call the method to test
        at2.generateICSFile(data, path);
    }

    
}
