package web.crud.controller;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainControllerTest {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private final String url = "http://localhost:8080";
    private WebElement actionElem;
    private WebElement nameElem;
    private WebElement emailElem;
    private WebElement saveElem;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, 5);
    }

    // Create
    @Order(1)
    @Test
    void testCreate() {
        // Open the URL
        driver.get(url);

        // Find, wait and click Create a customer
        driverWait.until(ExpectedConditions.elementToBeClickable(actionElem = driver.findElement(By.xpath
                ("//a[text()='Create a customer']"))));
        actionElem.click();

        waitElems();

        // Enter the data and click Save
        nameElem.sendKeys("Test");
        emailElem.sendKeys("test@email.org");
        saveElem.click();
    }

    // Update
    @Order(2)
    @Test
    void testUpdate() {
        // Open the URL
        driver.get(url);

        // Find, wait and click 🔧 at Test
        driverWait.until(ExpectedConditions.elementToBeClickable(actionElem = driver.findElement(By.xpath
                ("//tr[td[text()='Test']]//a[text()='\uD83D\uDD27']"))));
        actionElem.click();

        waitElems();

        // Clear, enter the data and click Save
        nameElem.clear();
        nameElem.sendKeys("Test 2");
        emailElem.clear();
        emailElem.sendKeys("test2@email.org");
        saveElem.click();
    }

    // Delete
    @Order(3)
    @Test
    void testDelete() {
        // Open the URL
        driver.get(url);

        // Find, wait and click ❌ at Test 2
        driverWait.until(ExpectedConditions.elementToBeClickable(actionElem = driver.findElement(By.xpath
                ("//tr[td[text()='Test 2']]//a[text()='\u274C']"))));
        actionElem.click();
    }

    @AfterEach
    void cleanUp() {
        driver.quit();
    }

    // Find and wait for the elements
    void waitElems() {
        driverWait.until(ExpectedConditions.elementToBeClickable(nameElem = driver.findElement(By.name("name"))));
        driverWait.until(ExpectedConditions.elementToBeClickable(emailElem = driver.findElement(By.name("email"))));
        driverWait.until(ExpectedConditions.elementToBeClickable(saveElem = driver.findElement(By.xpath
                ("//input[@value='Save']"))));
    }

}
