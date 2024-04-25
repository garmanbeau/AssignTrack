package com.se470.assigntrack2;
/**
 *
 * @author travisloukusa
 */

import javax.swing.*;
import java.awt.*;
import net.fortuna.ical4j.model.DateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;
import java.util.List;

public class AssignTrack2 extends JFrame {
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JComboBox<String> browserSelect;
    private JButton submitButton;
    
    private IcsCalendarManager calendarManager;
    private String calendarData;
    
    public AssignTrack2() {
        initUI();
        
        calendarManager = new IcsCalendarManager();
        
        setTitle("Web Scraper");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    private void initUI() {
        add(new JLabel("Username:"));
        userTextField = new JTextField(20);
        add(userTextField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        add(new JLabel("Browser:"));
        browserSelect = new JComboBox<>(new String[]{"Safari", "Chrome"});
        browserSelect.setSelectedItem("Chrome"); // Default selection
        add(browserSelect);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> scrapeData());
        add(submitButton);
    }

    private void scrapeData() {
    String username = userTextField.getText();
    String password = new String(passwordField.getPassword());
    String browser = (String) browserSelect.getSelectedItem();
    String data = WebScraper.scrape(username, password, browser);
    calendarData = data;

    if (calendarData != "" && calendarData != null){
    // Show a confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Scrape Successful! Download File?", "Scrape Result", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
        // If the user chooses "Yes", open a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choice = fileChooser.showSaveDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            // If the user selects a directory, generate the .ics file in that directory
            String path = fileChooser.getSelectedFile().getAbsolutePath() + "\\assignments.ics";
            generateICSFile(calendarData, path);
        }
    }
    }

    // Clear fields after action
    userTextField.setText("");
    passwordField.setText("");
}

    public void generateICSFile(String data, String path){
    // Parse the JSON data
    JSONArray jsonArray = new JSONArray(data);

    // Iterate over the JSON objects
    for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);

        // Extract the Title, Class, and Due Date from the JSON object
        String title = jsonObject.getString("Title");
        String details = jsonObject.getString("Class");
        String dueDateString = jsonObject.getString("Due Date");

        // Convert the Due Date string to a DateTime
        DateTime endDate = AssignTrack.dateConverter(dueDateString);

        // Add the event to the calendar
        calendarManager.addEvent(title, endDate, details);
    }

    // Generate and save the .ics file
    calendarManager.generateAndSaveIcsFile(path);
}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AssignTrack2().setVisible(true));
    }
    
    public IcsCalendarManager getCalendarManager(){
        return calendarManager;
    }
    
}