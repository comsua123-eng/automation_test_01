package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01() {
        // Description: Verify all checkboxes can be selected
        // Steps: Navigate to site -> Click all checkboxes
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Expected: All checkboxes should be selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "Checkbox '" + checkbox.getAttribute("id") + "' is not selected.");
        }
    }

    @Test
    public void TC02() {
        // Description: Verify all checkboxes can be deselected
        // Steps: Navigate to site -> Select all -> Deselect all
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Select all checkboxes first (as per "Select all" in steps)
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // Ensure they are selected before deselecting (optional check)
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "Pre-check: Checkbox '" + checkbox.getAttribute("id") + "' was not selected.");
        }

        // Deselect all
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Expected: All checkboxes should be deselected
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected(), "Checkbox '" + checkbox.getAttribute("id") + "' is still selected.");
        }
    }

    @AfterClass
    public void cleanBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}