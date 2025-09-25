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
import java.util.List; // Although not used in this specific TC, it's part of the standard imports from the example.

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
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)"); // Scroll to ensure visibility

        // Steps
        // Locate the checkbox labeled 'Thyroid Problems'.
        WebElement thyroidProblemsCheckbox = driver.findElement(By.id("thyroid"));

        // If the 'Thyroid Problems' checkbox is already selected, click it once to deselect it.
        if (thyroidProblemsCheckbox.isSelected()) {
            thyroidProblemsCheckbox.click();
            System.out.println("'Thyroid Problems' checkbox was initially selected, deselected it.");
        }

        // Click the 'Thyroid Problems' checkbox.
        thyroidProblemsCheckbox.click();
        System.out.println("Clicked 'Thyroid Problems' checkbox.");

        // Expected Result
        // The 'Thyroid Problems' checkbox should be visible
        Assert.assertTrue(thyroidProblemsCheckbox.isDisplayed(), "Thyroid Problems checkbox should be visible.");

        // and, after clicking, its state must be 'selected = true'.
        Assert.assertTrue(thyroidProblemsCheckbox.isSelected(), "Thyroid Problems checkbox state must be 'selected = true'.");
        System.out.println("'Thyroid Problems' checkbox is now selected: " + thyroidProblemsCheckbox.isSelected());
    }

    @AfterClass
    public void cleanBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}