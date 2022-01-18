package Task1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task1Tests {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bbc.com");
    }

    @Test(priority = 1)
    public void task1() {
        WebElement elementWeather = driver.findElement(By.xpath("//div[@class='weather--location']/h2"));
        elementWeather.click();
        String elementText = elementWeather.getText();
        assertEquals(elementText, "LONDON WEATHER", "\"LONDON WEATHER\" is not equals elementText.");
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
