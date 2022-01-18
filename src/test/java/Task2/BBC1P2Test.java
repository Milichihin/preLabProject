package Task2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BBC1P2Test {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1300, 820));
        driver.get("https://www.bbc.com/news/52143212");
    }

    @Test(priority = 1)
    public void verifyForEmptyField() {

        WebElement accept = driver.findElement(By.xpath("//input[@type='checkbox']"));
        WebElement question = driver.findElement(By.xpath("//textarea"));
        WebElement name = driver.findElement(By.xpath("//input[@aria-label='Name']"));
        WebElement email = driver.findElement(By.xpath("//input[@aria-label='Email address']"));
        WebElement phone = driver.findElement(By.xpath("//input[@aria-label='Contact number']"));
        WebElement location = driver.findElement(By.xpath("//input[@aria-label='Location ']"));
        WebElement age = driver.findElement(By.xpath("//input[@aria-label='Age']"));
        WebElement submit = driver.findElement(By.xpath("//button[@class='button']"));

        new WebDriverWait(driver, 10)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        Boolean isModal = driver.findElements(By.xpath("//button[@class='tp-close tp-active']")).size() > 0;
        if (isModal) {
            driver.findElement(By.xpath("//button[@class='tp-close tp-active']")).click();
        }

        accept.click();
        question.sendKeys(Keys.chord(Keys.CONTROL, "a"), "");
        name.sendKeys(Keys.chord(Keys.CONTROL, "a"), "qwerty");
        email.sendKeys(Keys.chord(Keys.CONTROL, "a"), "qwerty");
        phone.sendKeys(Keys.chord(Keys.CONTROL, "a"), "qwerty");
        location.sendKeys(Keys.chord(Keys.CONTROL, "a"), "qwerty");
        age.sendKeys(Keys.chord(Keys.CONTROL, "a"), "qwerty");

        submit.click();

        driver.manage().timeouts().implicitlyWait(2, SECONDS);

        Boolean isError = driver.findElements(By.xpath("//div[@class='input-error-message']")).size() > 0;

        assertTrue(isError);
    }


    @Test(priority = 2)
    public void verifyForEmptyDifferentField() {


        WebElement accept = driver.findElement(By.xpath("//input[@type='checkbox']"));
        WebElement question = driver.findElement(By.xpath("//textarea"));
        WebElement name = driver.findElement(By.xpath("//input[@aria-label='Name']"));
        WebElement email = driver.findElement(By.xpath("//input[@aria-label='Email address']"));
        WebElement phone = driver.findElement(By.xpath("//input[@aria-label='Contact number']"));
        WebElement location = driver.findElement(By.xpath("//input[@aria-label='Location ']"));
        WebElement age = driver.findElement(By.xpath("//input[@aria-label='Age']"));
        WebElement submit = driver.findElement(By.xpath("//button[@class='button']"));

        List<WebElement> errorElements;
        ArrayList<String> arrayOfErrors = new ArrayList<String>();

        new WebDriverWait(driver, 10)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        Boolean isModal = driver.findElements(By.xpath("//button[@class='tp-close tp-active']")).size() > 0;
        if (isModal) {
            driver.findElement(By.xpath("//button[@class='tp-close tp-active']")).click();
        }

        ArrayList<WebElement> arrayOfRequiredFields = new ArrayList<>();
        arrayOfRequiredFields.add(accept);
        arrayOfRequiredFields.add(question);
        arrayOfRequiredFields.add(name);
        arrayOfRequiredFields.add(email);


        phone.sendKeys("qwerty");
        location.sendKeys("qwerty");
        age.sendKeys("qwerty");

        int random = (int) (Math.random() * (arrayOfRequiredFields.size()));

        for (int i = 0; i < arrayOfRequiredFields.size(); i++) {
            if (i == random && arrayOfRequiredFields.get(i) != accept) {
                arrayOfRequiredFields.get(i).sendKeys("");
            } else if (i != random && arrayOfRequiredFields.get(i) == accept) {
                arrayOfRequiredFields.get(i).click();
            } else if (i != random && arrayOfRequiredFields.get(i) != accept) {
                arrayOfRequiredFields.get(i).sendKeys("qwerty");
            }
        }

        submit.click();

        driver.manage().timeouts().implicitlyWait(2, SECONDS);

        Boolean isError = driver.findElements(By.xpath("//div[@class='input-error-message']")).size() > 0;
        if (isError) {
            errorElements = driver.findElements(By.xpath("//div[@class='input-error-message']"));
            for (WebElement elem : errorElements) {
                arrayOfErrors.add(elem.getText());
            }
        }

        assertTrue(isError);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
