package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TC012_CheckboxResponsivenessTest {

    private WebDriver driver;
    private String baseUrl = "https://mitec-eat-easy.vercel.app/";
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
        driver.manage().window().maximize(); // Start with a desktop-like view
    }

    @Test
    public void verifyCheckboxResponsiveness() throws InterruptedException {
        // Locators for key elements
        By espressoCheckboxLocator = By.id("espresso");
        By caramelSyrupCheckboxLocator = By.id("caramelSyrup");
        By yourOrderHeadingLocator = By.xpath("//h2[contains(text(),'Your Order:')]");
        By yourOrderContentContainerLocator = By.cssSelector("div.md\\:w-1\\/2.p-4.bg-gray-50");

        // Initial checks in desktop view (maximized)
        WebElement espressoCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(espressoCheckboxLocator));
        WebElement caramelSyrupCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(caramelSyrupCheckboxLocator));
        WebElement yourOrderHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(yourOrderHeadingLocator));
        WebElement yourOrderContentContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(yourOrderContentContainerLocator));

        Assert.assertTrue(espressoCheckbox.isDisplayed(), "Espresso checkbox is displayed in desktop view.");
        Assert.assertTrue(espressoCheckbox.isEnabled(), "Espresso checkbox is enabled in desktop view.");
        Assert.assertTrue(yourOrderHeading.isDisplayed(), "Your Order heading is displayed in desktop view.");
        Assert.assertTrue(yourOrderContentContainer.isDisplayed(), "Your Order content container is displayed in desktop view.");

        // Step 2: Resize the browser window to a smaller width (e.g., simulate a mobile device)
        Dimension mobileSize = new Dimension(400, 800);
        driver.manage().window().setSize(mobileSize);
        Thread.sleep(2000); // Give browser time to adapt UI

        // Step 3: Interact with checkboxes (select) in the smaller view
        // Re-locate elements to ensure they are found in the new viewport
        espressoCheckbox = wait.until(ExpectedConditions.elementToBeClickable(espressoCheckboxLocator));
        caramelSyrupCheckbox = wait.until(ExpectedConditions.elementToBeClickable(caramelSyrupCheckboxLocator));

        if (!espressoCheckbox.isSelected()) {
            espressoCheckbox.click();
        }
        if (!caramelSyrupCheckbox.isSelected()) {
            caramelSyrupCheckbox.click();
        }

        // Verify selection state in small view
        Assert.assertTrue(espressoCheckbox.isSelected(), "Espresso checkbox is selected in small view.");
        Assert.assertTrue(caramelSyrupCheckbox.isSelected(), "Caramel Syrup checkbox is selected in small view.");

        // Expected 1: Checkboxes and their corresponding labels remain legible, correctly aligned, and fully visible at all screen sizes.
        // (Legibility and alignment are subjective and often require visual testing tools. We assert visibility and accessibility.)
        Assert.assertTrue(espressoCheckbox.isDisplayed(), "Espresso checkbox remains displayed in small view.");
        Assert.assertTrue(espressoCheckbox.isEnabled(), "Espresso checkbox remains accessible in small view.");
        Assert.assertTrue(caramelSyrupCheckbox.isDisplayed(), "Caramel Syrup checkbox remains displayed in small view.");
        Assert.assertTrue(caramelSyrupCheckbox.isEnabled(), "Caramel Syrup checkbox remains accessible in small view.");

        // Expected 3: The 'Your Order:' section content adapts gracefully without overlapping or truncating.
        yourOrderHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(yourOrderHeadingLocator));
        yourOrderContentContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(yourOrderContentContainerLocator));
        Assert.assertTrue(yourOrderHeading.isDisplayed(), "Your Order heading is displayed in small view.");
        Assert.assertTrue(yourOrderContentContainer.isDisplayed(), "Your Order content container is displayed in small view.");

        // Verify order items are displayed (if any)
        List<WebElement> orderItemsSmall = yourOrderContentContainer.findElements(By.tagName("li"));
        if (!orderItemsSmall.isEmpty()) {
            for (WebElement item : orderItemsSmall) {
                Assert.assertTrue(item.isDisplayed(), "Order item is displayed in small view.");
            }
        } else {
             System.out.println("No order items found in small view (which is expected if no items are added).");
        }
        
        // Expected 4: All interactive elements remain accessible. (Checked via isEnabled() and isDisplayed())

        // Step 4: Resize the browser window back to a larger width (e.g., simulate a desktop device).
        Dimension desktopSize = new Dimension(1366, 768);
        driver.manage().window().setSize(desktopSize);
        Thread.sleep(2000); // Give browser time to adapt UI

        // Re-locate elements after resizing
        espressoCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(espressoCheckboxLocator));
        caramelSyrupCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(caramelSyrupCheckboxLocator));
        yourOrderHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(yourOrderHeadingLocator));
        yourOrderContentContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(yourOrderContentContainerLocator));

        // Expected 1: Checkboxes and their corresponding labels remain legible, correctly aligned, and fully visible at all screen sizes.
        Assert.assertTrue(espressoCheckbox.isDisplayed(), "Espresso checkbox remains displayed in large view.");
        Assert.assertTrue(espressoCheckbox.isEnabled(), "Espresso checkbox remains accessible in large view.");
        Assert.assertTrue(caramelSyrupCheckbox.isDisplayed(), "Caramel Syrup checkbox remains displayed in large view.");
        Assert.assertTrue(caramelSyrupCheckbox.isEnabled(), "Caramel Syrup checkbox remains accessible in large view.");

        // Expected 2: Selected/deselected states are maintained when resizing.
        Assert.assertTrue(espressoCheckbox.isSelected(), "Espresso checkbox state maintained (selected) after resizing to large view.");
        Assert.assertTrue(caramelSyrupCheckbox.isSelected(), "Caramel Syrup checkbox state maintained (selected) after resizing to large view.");

        // Expected 3: The 'Your Order:' section content adapts gracefully without overlapping or truncating.
        Assert.assertTrue(yourOrderHeading.isDisplayed(), "Your Order heading is displayed in large view.");
        Assert.assertTrue(yourOrderContentContainer.isDisplayed(), "Your Order content container is displayed in large view.");
        
        // Verify order items are displayed (if any)
        List<WebElement> orderItemsLarge = yourOrderContentContainer.findElements(By.tagName("li"));
        if (!orderItemsLarge.isEmpty()) {
            for (WebElement item : orderItemsLarge) {
                Assert.assertTrue(item.isDisplayed(), "Order item is displayed in large view.");
            }
        } else {
            System.out.println("No order items found in large view (which is expected if no items are added).");
        }

        // Expected 4: All interactive elements remain accessible. (Checked via isEnabled() and isDisplayed())
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}