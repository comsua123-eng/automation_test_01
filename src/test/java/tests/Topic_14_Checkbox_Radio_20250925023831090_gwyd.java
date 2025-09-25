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

public class Topic_14_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_CHK_003() {
        // Description: Verify 'Reading' checkbox can be checked and unchecked.
        driver.get("https://automationfc.github.io/multiple-fields/");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        // Find the 'Reading' checkbox
        WebElement readingCheckbox = driver.findElement(By.id("reading"));

        // Step 1: Verify the 'Reading' checkbox is initially unchecked.
        Assert.assertFalse(readingCheckbox.isSelected(), "Expected 'Reading' checkbox to be unchecked initially.");

        // Step 2: Click on the 'Reading' checkbox.
        readingCheckbox.click();

        // Expected Result 1: After the first click, it should be checked.
        Assert.assertTrue(readingCheckbox.isSelected(), "Expected 'Reading' checkbox to be checked after the first click.");

        // Step 3: Click on the 'Reading' checkbox again.
        readingCheckbox.click();

        // Expected Result 2: After the second click, it should be unchecked.
        Assert.assertFalse(readingCheckbox.isSelected(), "Expected 'Reading' checkbox to be unchecked after the second click.");
    }

    @AfterClass
    public void cleanBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}