/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se470.assigntrack;

/**
 *
 * @author garma
 */
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class IcsCalendarManager {

    private Calendar calendar; // The main calendar object

    public IcsCalendarManager() {
        // Initialize the calendar when creating an instance of the manager
        calendar = new Calendar();
        // Set the PRODID property (replace with your own unique identifier)
        calendar.getProperties().add(new ProdId("-//AssignTrack//EN"));
        // Set the VERSION property
        calendar.getProperties().add(Version.VERSION_2_0);
    }

    public void addEvent(String title, DateTime endDate, String details) {

    DateTime startDate = endDate;

    VEvent event = new VEvent(startDate, endDate, title);
    event.getProperties().add(new Uid(UUID.randomUUID().toString()));
    
    // Add the details as a DESCRIPTION property
    event.getProperties().add(new Description(details));

    calendar.getComponents().add(event);
}


    public void generateAndSaveIcsFile(String filePath) {
        try {
            File outputFile = new File(filePath);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, outputStream);

            outputStream.close();
            System.out.println("Generated .ics file saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
