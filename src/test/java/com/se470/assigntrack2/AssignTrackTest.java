/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.se470.assigntrack2;

import net.fortuna.ical4j.model.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import net.fortuna.ical4j.model.component.VEvent;

/**
 *
 * @author garma
 */
public class AssignTrackTest {
    
    public AssignTrackTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dateConverter method, of class AssignTrack. Test path 1
     */
    @Test
    public void testDateConverterPath1() {
        String inputDate = "";
        DateTime expResult = null;
        DateTime result = AssignTrack.dateConverter(inputDate);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDateConverterPath2() {
        String inputDate = "Feb 28, 2024 11:00 AM";
        String expResult = "20240228T110000";
        DateTime result = AssignTrack.dateConverter(inputDate);
        System.out.print(result.toString());
        assertEquals(expResult, result.toString());
    }
    
    @Test
    public void testAddEventPath1() {
        IcsCalendarManager manager = new IcsCalendarManager();
        DateTime endDate = new DateTime();
        String title = "Test Event";
        String details = "This is a test event.";

        // Call the method to test
        manager.addEvent(title, endDate, details);

        // Check that an event was added to the calendar
        boolean eventFound = false;
        for (Object component : manager.getCalendar().getComponents()) {
            if (component instanceof VEvent) {
                VEvent event = (VEvent) component;
                if (title.equals(event.getSummary().getValue()) &&
                    details.equals(event.getDescription().getValue()) &&
                    endDate.equals(event.getStartDate().getDate())) {
                    eventFound = true;
                    break;
                }
            }
        }

        assertTrue("Event was not added to the calendar", eventFound);
    }
}
    
