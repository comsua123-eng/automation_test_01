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
    public void TC02() {
        // Description: Verify that all previously selected checkboxes can be deselected.

        // Steps: Navigate to the test site: https://automationfc.github.io/multiple-fields/
        driver.get("https://automationfc.github.io/multiple-fields/");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)"); // Scroll for visibility

        // Steps: First, identify all available checkboxes and click each one to ensure they are all selected.
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Click each checkbox if not already selected to ensure they are all selected
        System.out.println("Selecting all checkboxes...");
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                System.out.println("  Clicked to select: " + checkbox.getAttribute("id"));
            }
            // Assert that it is now selected
            Assert.assertTrue(checkbox.isSelected(), "Checkbox '" + checkbox.getAttribute("id") + "' should be selected after initial click.");
        }
        System.out.println("All checkboxes verified as selected.");


        // Steps: Click each selected checkbox again to deselect it.
        System.out.println("Deselecting all checkboxes...");
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) { // Only click if it's currently selected to deselect it
                checkbox.click();
                System.out.println("  Clicked to deselect: " + checkbox.getAttribute("id"));
            }
        }
        System.out.println("Attempted to deselect all checkboxes.");

        // Expected Result: All checkboxes on the page should be in a 'selected=false' state. No checkbox should remain selected.
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected(), "Checkbox '" + checkbox.getAttribute("id") + "' should be deselected, but it is still selected.");
            System.out.println("  Verified deselected: " + checkbox.getAttribute("id"));
        }
        System.out.println("All checkboxes successfully verified as deselected.");
    }

    @AfterClass
    public void cleanBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}