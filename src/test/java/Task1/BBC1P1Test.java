package Task1;

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

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BBC1P1Test {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1300, 820));

        driver.get("https://www.bbc.com");
    }

    @Test(priority = 1)
    public void testMainArticle() {

        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));//wait for page loading

        driver.findElement(By.xpath("//a[@class='module__title__link tag tag--news']")).click();


        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));//wait for page loading

        String actualArticleText = driver.findElement(By.xpath("//h3[@class='gs-c-promo-heading__title gel-paragon-bold nw-o-link-split__text']")).getText();
        String testArticleText = "Tonga runway ash blocks New Zealand air relief";

        assertEquals(actualArticleText, testArticleText, "Your ArticleText is not equals expected");
    }

    @Test(priority = 2)
    public void testAllArticles() {
        List <String> articlesForCheck = Arrays.asList(
                "Tonga runway ash blocks New Zealand air relief",
                "",
                "'If I find a body, I recover a piece of my son'",
                "Rabbi threw chair to escape Texas synagogue gunman",
                "UK says it is sending weapons to defend Ukraine",
                "HK police charge former aircrew over Covid rules",
                "Body of missing four-year-old Belgian boy found",
                "Afghanistan twin quakes kill at least 22",
                "Suspect identified over Anne Frank's betrayal",
                "Plastic crisis needs binding treaty, report says",
                "French far-right candidate guilty of hate speech",
                "",
                "",
                "BBC licence fee to be frozen at £159 for two years",
                "More MI5 alerts to come to counter foreign interference",
                "Australian Open: Murray v Basilashvili - radio & text",
                "UK job vacancies hit new high after furlough ends"
        );

        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        driver.findElement(By.xpath("//a[@class='module__title__link tag tag--news']")).click();

        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        List<WebElement> arrOfElements = driver.findElements(By.xpath("//div[@class='gel-wrap gs-u-pt+']//h3"));
        ArrayList<String> arrOfArticles = new ArrayList<>();
        for (WebElement el: arrOfElements ) {
            arrOfArticles.add(el.getText());
        }

        boolean check = false;
        int checkValueArticle = 0;
        int checkValueArticleActual = 0;

        for (String article: articlesForCheck ) {
            if (article != "") {
                checkValueArticle++;
                for (String articleActual: arrOfArticles ) {
                    if (article != "" && articleActual != "" && articleActual.contains(article)) {
                        checkValueArticleActual++;
                    } else continue;
                }
            } else continue;
        }

        if (checkValueArticleActual == checkValueArticle) check = true;

        assertEquals(check, true);

    }

    @Test(priority = 3)
    public void compareWordInSearch() {
        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));//wait for page loading
        driver.findElement(By.xpath("//a[@class='module__title__link tag tag--news']")).click();

        new WebDriverWait(driver, 3)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));//wait for page loading

        List<WebElement> menuElements = driver
                .findElements(By.xpath("//ul[@class='gs-o-list-ui--top-no-border nw-c-nav__wide-sections']//a[@class='nw-o-link']"));
        ArrayList<String> menuArticles = new ArrayList<String>();

        for ( WebElement elem: menuElements ) {
            if (elem.getText().contains("Home")) {
                menuArticles.add("Home");
            } else menuArticles.add(elem.getText());
        }

        boolean check = false;

        for ( String article: menuArticles ) {
            driver.findElement(By.xpath("//input[@id='orb-search-q']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), article);
            driver.findElement(By.xpath("//button[@id='orb-search-button']")).click();

            new WebDriverWait(driver, 3)
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));//wait for page loading

            String firsArticle = driver.findElement(By.xpath("//p[1]")).getText();
            if ( firsArticle.contains(article)) {
                check = true;
            } else {
                break;
            }
            driver.get("https://www.bbc.com");
        }

        assertEquals(check, true);

    }



    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
