/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javawebscraper;

/**
 *
 * @author travisloukusa
 */



import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JComboBox<String> browserSelect;
    private JButton submitButton;

    public App() {
        initUI();
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
        JOptionPane.showMessageDialog(this, "Data scraped: " + data, "Scrape Result", JOptionPane.INFORMATION_MESSAGE);
        // Clear fields after action
        userTextField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
