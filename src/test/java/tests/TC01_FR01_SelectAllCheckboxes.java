package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TC01_FR01_SelectAllCheckboxes {

    private WebDriver driver;
    private final String TEST_SITE_URL = "https://automationfc.github.io/multiple-fields/";

    @BeforeTest
    public void setUp() {
        // Set up Chrome WebDriver
        // You might need to set the path to your chromedriver executable
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize browser window
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
    }

    @Test
    public void verifySelectAllCheckboxes() {
        // 1. Navigate to the test site
        driver.get(TEST_SITE_URL);

        // 2. Locate and click the 'Select All' checkbox/button
        // The 'Select All' checkbox has an id 'check_all'
        WebElement selectAllCheckbox = driver.findElement(By.id("check_all"));
        if (!selectAllCheckbox.isSelected()) { // Only click if not already selected
            selectAllCheckbox.click();
        }

        // 3. Verify all individual checkboxes are selected
        // Find all input elements of type checkbox
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Iterate through all found checkboxes and assert they are selected
        for (WebElement checkbox : allCheckboxes) {
            // Get the value attribute for a more descriptive assertion message
            String checkboxValue = checkbox.getAttribute("value");
            Assert.assertTrue(checkbox.isSelected(), "Checkbox '" + checkboxValue + "' is not selected.");
        }
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}