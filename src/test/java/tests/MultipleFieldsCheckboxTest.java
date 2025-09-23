package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class MultipleFieldsCheckboxTest {

    private WebDriver driver;
    private final String TEST_SITE_URL = "https://automationfc.github.io/multiple-fields/";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(TEST_SITE_URL);
    }

    @Test(description = "TC01_FR01: Verify that all checkboxes can be selected simultaneously using the 'Select All' control.")
    public void TC01_FR01_verifySelectAllCheckboxes() {
        // Step 1: Navigate to the test site (done in @BeforeTest)

        // Step 2: Locate and click the 'Select All' checkbox
        WebElement selectAllCheckbox = driver.findElement(By.id("check_all"));
        if (!selectAllCheckbox.isSelected()) {
            selectAllCheckbox.click();
        }

        // Expected: All individual checkboxes on the page must be in a 'selected' (checked) state.
        // No checkbox should be left unselected.
        List<WebElement> individualCheckboxes = driver.findElements(By.xpath("//input[@name='checkbox[]']"));

        Assert.assertFalse(individualCheckboxes.isEmpty(), "No individual checkboxes found on the page.");

        for (WebElement checkbox : individualCheckboxes) {
            Assert.assertTrue(checkbox.isSelected(), "Checkbox with value '" + checkbox.getAttribute("value") + "' is not selected.");
        }
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}