package com.se470.assigntrack2;

import org.junit.Test;
import static org.junit.Assert.*;

public class WebScraperTest {

    /*This test will fail unless given valid credentials */
    @Test
    public void testScrape_Path1() {
        String user = "bn6503cm";
        String password = "";
        String browser = "Chrome";

        WebScraper scraper = new WebScraper();
        String result = scraper.scrape(user, password, browser);

        assertNotNull("The result should not be null", result);
        assertFalse("The result should not be empty", result.isEmpty());
    }

    @Test(expected = Exception.class)
    public void testScrape_Path2() {
        String user = "invalid_username";
        String password = "invalid_password";
        String browser = "Safari";

        WebScraper scraper = new WebScraper();
        scraper.scrape(user, password, browser);
        
        fail("No exception thrown");
    }
}
