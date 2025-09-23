package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MultipleFieldsTest {

    WebDriver driver;
    private final String TEST_SITE_URL = "https://automationfc.github.io/multiple-fields/";

    @BeforeTest
    public void setup() {
        // Set up WebDriver (e.g., ChromeDriver)
        // Ensure you have ChromeDriver installed and its path configured or in your system PATH
        // System.setProperty("webdriver.chrome.driver", "path/to/your/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_FR01_verifySelectAllCheckboxes() {
        // Navigate to the test site
        driver.get(TEST_SITE_URL);

        // Find the 'All' checkbox and click it
        WebElement allCheckbox = driver.findElement(By.id("cb_all"));
        if (!allCheckbox.isSelected()) {
            allCheckbox.click();
        }

        // Verify that the 'All' checkbox itself is selected
        Assert.assertTrue(allCheckbox.isSelected(), "The 'All' checkbox should be selected after clicking.");

        // Find all individual checkboxes (excluding the 'All' checkbox by its specific ID)
        // We can find all input type='checkbox' elements and then iterate.
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Iterate through all checkboxes and assert that each one is selected
        // We ensure that ALL checkboxes are selected, including 'cb_all' itself which is part of the list
        for (WebElement checkbox : checkboxes) {
            String checkboxName = checkbox.getAttribute("name"); // Get name for better assertion message
            Assert.assertTrue(checkbox.isSelected(), "Checkbox with name '" + checkboxName + "' should be selected.");
        }
    }

    @AfterTest
    public void teardown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}