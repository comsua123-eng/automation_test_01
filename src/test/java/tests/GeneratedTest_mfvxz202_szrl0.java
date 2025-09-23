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

    private WebDriver driver;
    private final String BASE_URL = "https://automationfc.github.io/multiple-fields/";

    @BeforeTest
    public void setup() {
        // Set up WebDriverManager to automatically download and configure ChromeDriver
        // WebDriverManager.chromedriver().setup(); // This line is for older versions of WebDriverManager, no longer strictly needed if using latest Selenium 4.6+
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Verify that clicking the 'All' checkbox selects all individual checkboxes on the page.")
    public void TC01_FR01_SelectAllCheckboxes() {
        // Navigate to the test site
        driver.get(BASE_URL);

        // Click on the 'All' checkbox at the top of the list.
        WebElement allCheckbox = driver.findElement(By.id("all"));
        if (!allCheckbox.isSelected()) {
            allCheckbox.click();
        }

        // Observe the state of all other individual checkboxes
        // Find all checkboxes on the page (including 'All')
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        // Expected: All individual checkboxes on the page, along with the 'All' checkbox itself, are in a selected state (checked=true).
        for (WebElement checkbox : checkboxes) {
            System.out.println("Checking checkbox with name/id: " + checkbox.getAttribute("name") + " or " + checkbox.getAttribute("id"));
            Assert.assertTrue(checkbox.isSelected(), "Checkbox with ID/name " + checkbox.getAttribute("id") + "/" + checkbox.getAttribute("name") + " should be selected.");
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}