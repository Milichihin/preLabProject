package Task2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;

public class LoremipsumP2Test {
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
        boolean check = false;
        String[] arrOfAmountOfWords = new String[] {"1", "0", "5", "10", "20"};

        for ( String amount: arrOfAmountOfWords ) {
            new WebDriverWait(driver, 3)
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            driver.findElement(By.xpath("//label[@for='words']")).click();
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), amount);
            driver.findElement(By.xpath("//input[@name='generate']")).click();
            new WebDriverWait(driver, 3)
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            String generatedText = driver.findElement(By.xpath("//div[@id='lipsum']/p")).getText();

            if (amount == "0" || amount == "1") {
                check = true;
            } else {
                int count = 1;
                for(int i = 0; i < generatedText.length(); i++) {
                    if( generatedText.charAt(i) == ' ' && generatedText.charAt(i) != generatedText.charAt(i-1) ) {
                        count++;
                    }
                    if( i == generatedText.length()-1 && generatedText.charAt(generatedText.length() - 1) == ' ' ) {
                        count--;
                    }
                }
                if( Integer.parseInt(amount) == count) check = true;
            }
            driver.get("https://lipsum.com/");

        } // loop finished


        assertEquals(check, true);

    }

    @Test(priority = 2)
    public void containsBytes() {
        boolean check = false;
        char [] arrOfAmountOfChar = new char [] {'0', '1', '2', '5'};

        for ( char amount: arrOfAmountOfChar ) { // loop starts

            new WebDriverWait(driver, 3)
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            driver.findElement(By.xpath("//label[@for='bytes']")).click();
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Character.toString(amount));
            driver.findElement(By.xpath("//input[@name='generate']")).click();
            new WebDriverWait(driver, 3)
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            String generatedText = driver.findElement(By.xpath("//div[@id='lipsum']/p")).getText();

            if (amount == '0' || amount == '1' || amount == '2' || amount == '3') {
                check = true;
            } else {
                int count = 0;
                for(int i = 0; i < generatedText.length(); i++) {
                    count++;
                }
                if( Character.getNumericValue(amount) == count) check = true;
            }
            driver.get("https://lipsum.com/");

        } // loop finished


        assertEquals(check, true);

    }


    @Test(priority = 3)
    public void startsWithoutLorem() {
        driver.findElement(By.xpath("//label[@for='start']")).click();
        driver.findElement(By.xpath("//input[@name='generate']")).click();
        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        String testPhrase = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        String paragraphText = driver.findElement(By.xpath("//div[@id='lipsum']/p[1]"))
                .getText()
                .substring(0, testPhrase.length());

        boolean check = false;
        if (paragraphText != testPhrase) check = true;

        assertEquals(check, true);
    }


    @Test(priority = 4)
    public void percentOfLorem() {

        double i = 0;
        int countOfLorem = 0;

        while ( i <= 10) {
            i++;
            driver.findElement(By.xpath("//input[@name='generate']")).click();
            new WebDriverWait(driver, 3)
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            List<WebElement> elementsWithLorem1 = driver.findElements(xpath("//p[contains (text(), 'lorem')]"));
            List<WebElement> elementsWithLorem2 = driver.findElements(xpath("//p[contains (text(), 'Lorem')]"));

            countOfLorem += elementsWithLorem1.size()+elementsWithLorem2.size();
            driver.get("https://lipsum.com/");
        }
        double averageCountOfLorem = countOfLorem / i;
        boolean check = false;
        if(averageCountOfLorem >= 2) check = true;

        assertEquals(check, true);

    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
