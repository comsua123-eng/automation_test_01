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
import java.util.List; // Not strictly needed for this TC, but kept from the example skeleton

public class Topic_14_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC04_FR04() {
        // Description: Verify that the 'Thyroid Problems' checkbox can be selected independently.
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Locate the checkbox labeled 'Thyroid Problems'.
        // Assuming the ID "freetext_1" corresponds to the 'Thyroid Problems' checkbox based on site inspection.
        WebElement thyroidCheckbox = driver.findElement(By.id("freetext_1"));

        // Scroll the element into view to ensure visibility and interactivity
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thyroidCheckbox);
        // Optional: Add a small pause if scrolling can cause timing issues, though implicit wait should cover most cases.
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // If the 'Thyroid Problems' checkbox is already selected, click it once to deselect it.
        if (thyroidCheckbox.isSelected()) {
            thyroidCheckbox.click();
            System.out.println("Thyroid Problems checkbox was pre-selected, deselected it.");
            Assert.assertFalse(thyroidCheckbox.isSelected(), "Pre-selected checkbox should be deselected.");
        }

        // Click the 'Thyroid Problems' checkbox.
        thyroidCheckbox.click();
        System.out.println("Thyroid Problems checkbox clicked to select.");

        // Expected Result: The 'Thyroid Problems' checkbox should be visible and, after clicking, its state must be 'selected = true'.
        Assert.assertTrue(thyroidCheckbox.isDisplayed(), "Thyroid Problems checkbox should be visible.");
        Assert.assertTrue(thyroidCheckbox.isSelected(), "Thyroid Problems checkbox state must be 'selected = true'.");
        System.out.println("Thyroid Problems checkbox is visible and selected.");
    }

    @AfterClass
    public void cleanBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}