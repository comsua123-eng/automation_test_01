package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC027_ChainOperationsTest {

    private WebDriver driver;
    private final String TEST_SITE_URL = "https://automationfc.github.io/multiple-fields/";

    // Locators
    private final By ITEM1_CHECKBOX = By.id("item1");
    private final By ITEM2_CHECKBOX = By.id("item2");
    private final By ITEM3_CHECKBOX = By.id("item3"); // Added for Select All and initial state check
    private final By RESET_BUTTON = By.xpath("//button[text()='Reset']");
    private final By SELECT_ALL_BUTTON = By.xpath("//button[text()='Select All']");
    private final By RESULT_DISPLAY = By.id("result");

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(TEST_SITE_URL);
    }

    @Test
    public void verifyChainOfOperations() {
        // Ensure initial state (all unchecked) by clicking Reset and verifying
        driver.findElement(RESET_BUTTON).click();
        Assert.assertFalse(driver.findElement(ITEM1_CHECKBOX).isSelected(), "Item 1 should be unchecked initially.");
        Assert.assertFalse(driver.findElement(ITEM2_CHECKBOX).isSelected(), "Item 2 should be unchecked initially.");
        Assert.assertFalse(driver.findElement(ITEM3_CHECKBOX).isSelected(), "Item 3 should be unchecked initially.");
        Assert.assertEquals(driver.findElement(RESULT_DISPLAY).getText(), "", "Result display should be empty initially.");

        // Step: Click to check 'Item 1' and 'Item 2'.
        driver.findElement(ITEM1_CHECKBOX).click();
        driver.findElement(ITEM2_CHECKBOX).click();
        Assert.assertTrue(driver.findElement(ITEM1_CHECKBOX).isSelected(), "Item 1 should be checked.");
        Assert.assertTrue(driver.findElement(ITEM2_CHECKBOX).isSelected(), "Item 2 should be checked.");
        Assert.assertEquals(driver.findElement(RESULT_DISPLAY).getText(), "Item 1, Item 2", "Result display should show 'Item 1, Item 2'.");

        // Step: Click the 'Reset' button.
        driver.findElement(RESET_BUTTON).click();

        // Expected: After 'Reset': All checkboxes should be unchecked, 'Result' should be empty.
        Assert.assertFalse(driver.findElement(ITEM1_CHECKBOX).isSelected(), "After Reset: Item 1 should be unchecked.");
        Assert.assertFalse(driver.findElement(ITEM2_CHECKBOX).isSelected(), "After Reset: Item 2 should be unchecked.");
        Assert.assertFalse(driver.findElement(ITEM3_CHECKBOX).isSelected(), "After Reset: Item 3 should be unchecked.");
        Assert.assertEquals(driver.findElement(RESULT_DISPLAY).getText(), "", "After Reset: Result display should be empty.");

        // Step: Click the 'Select All' button.
        driver.findElement(SELECT_ALL_BUTTON).click();

        // Expected: After 'Select All': All checkboxes should be checked, 'Result' display should show 'Item 1, Item 2, Item 3'.
        Assert.assertTrue(driver.findElement(ITEM1_CHECKBOX).isSelected(), "After Select All: Item 1 should be checked.");
        Assert.assertTrue(driver.findElement(ITEM2_CHECKBOX).isSelected(), "After Select All: Item 2 should be checked.");
        Assert.assertTrue(driver.findElement(ITEM3_CHECKBOX).isSelected(), "After Select All: Item 3 should be checked.");
        Assert.assertEquals(driver.findElement(RESULT_DISPLAY).getText(), "Item 1, Item 2, Item 3", "After Select All: Result display should show 'Item 1, Item 2, Item 3'.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}