/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.se470.assigntrack.src.main.java.com.mycompany.javawebscraper;

/**
 *
 * @author travisloukusa
 */


import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebScraper {

    public static String scrape(String user, String password, String browser) {
        WebDriver driver;
        // Choose the WebDriver based on the browser choice
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\garma\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            driver = new SafariDriver(); // Default to Safari
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        try {
            driver.get("https://www.stcloudstate.edu/advisingdays/d2l-login.aspx");
            WebElement usernameField = driver.findElement(By.id("userName"));
            WebElement passwordField = driver.findElement(By.id("password"));

            usernameField.sendKeys(user);
            passwordField.sendKeys(password);
            passwordField.sendKeys(Keys.RETURN);

            // Wait for the page to load sufficiently
            Thread.sleep(10000);

            // Navigate to the specific calendar page
            driver.get("https://stcloudstate.learn.minnstate.edu/d2l/le/calendar/1");

            List<Map<String, String>> assignments = new ArrayList<>();
            List<WebElement> assignmentsList = driver.findElements(By.cssSelector(".d2l-datalist-item.d2l-datalist-item-actionable.d2l-datalist-checkboxitem"));

            for (WebElement assignmentElement : assignmentsList) {
                WebElement content = assignmentElement.findElement(By.className("d2l-datalist-item-content"));
                String title = content.getAttribute("title");
                String className = content.findElement(By.cssSelector(".d2l-le-calendar-dot-circle")).getAttribute("title");
                String dueDate = content.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(2) > div:nth-child(1)")).getText();

                Map<String, String> assignment = new HashMap<>();
                assignment.put("Title", title);
                assignment.put("Class", className);
                assignment.put("Due Date", dueDate);
                assignments.add(assignment);
            }

            return new Gson().toJson(assignments);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            driver.quit();
        }
    }

    //public static void main(String[] args) {
        // Example usage with username, password, and browser type
        //String data = scrape("yourUsername", "yourPassword", "Chrome");
       // System.out.println(data);
   // }
}
