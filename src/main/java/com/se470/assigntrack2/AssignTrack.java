package com.se470.assigntrack2;
import net.fortuna.ical4j.model.DateTime;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author garma
 */


public class AssignTrack {

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
