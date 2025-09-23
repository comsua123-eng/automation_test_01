package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        
        // Find all checkbox elements
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Click each checkbox if it's not already selected
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Expected: All checkboxes should be selected
        // Assert that all checkboxes are in a 'selected=true' state
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "Checkbox '" + checkbox.getAttribute("id") + "' is not selected.");
        }
    }

    @Test
    public void TC02() {
        // Description: Verify that all previously selected checkboxes can be deselected.
        driver.get("https://automationfc.github.io/multiple-fields/");
        // Scroll down to ensure elements are in view, as often required for interactions
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        // Steps: Navigate to the test site: https://automationfc.github.io/multiple-fields/ ->
        // First, identify all available checkboxes and click each one to ensure they are all selected. ->
        // Click each selected checkbox again to deselect it.

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Step 1: Click each checkbox to ensure they are all selected
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            // Optional: Assert that it's selected after the first click
            Assert.assertTrue(checkbox.isSelected(), "Failed to select checkbox: " + checkbox.getAttribute("id"));
        }

        // Step 2: Click each selected checkbox again to deselect it
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Expected Result: All checkboxes on the page should be in a 'selected=false' state. No checkbox should remain selected.
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected(), "Checkbox '" + checkbox.getAttribute("id") + "' is still selected after deselection attempt.");
        }
    }

    @AfterClass
    public void cleanBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}