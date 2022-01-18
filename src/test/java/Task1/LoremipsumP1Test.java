package Task1;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoremipsumP1Test

{
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://lipsum.com/");
    }

    @Test(priority = 1)
    public void containsWord() {
        driver.findElement(By.xpath("//a[contains(@href, 'http://ru.lipsum.com/')]")).click();
        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        String testWord = "рыба";
        String paragraphText = driver.findElement(By.xpath("//div[@id='Panes']/div[1]/p[1]")).getText();
        assertTrue(paragraphText.contains(testWord));
    }

    @Test(priority = 2)
    public void startsWithLorem() {
        driver.findElement(By.xpath("//input[@name='generate']")).click();
        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        String testPhrase = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        String paragraphText = driver.findElement(By.xpath("//div[@id='lipsum']/p[1]"))
                .getText()
                .substring(0, testPhrase.length());
        assertEquals(paragraphText, testPhrase, "Your phrase is not equals expected");
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
