/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.se470.assigntrack;
import net.fortuna.ical4j.model.DateTime;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author garma
 */


public class AssignTrack {

    public static void main(String[] args) {

        IcsCalendarManager calendarManager = new IcsCalendarManager();

        // Parse dates using SimpleDateFormat
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM d, yyyy hh:mm a");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        
        DateTime start = dateConverter("Feb 28, 2024 10:00 AM");

            calendarManager.addEvent("Sample Event", start, "Class Software Class");
            calendarManager.generateAndSaveIcsFile("C:\\Users\\garma\\Downloads\\my_events.ics");
        
    }
        public static DateTime dateConverter(String inputDate){
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM d, yyyy hh:mm a");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date input = inputFormat.parse(inputDate);
            String output = outputFormat.format(input);

            Date outputDate = outputFormat.parse(output);
            DateTime start = new DateTime(outputDate);

            return start;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
        }
}
