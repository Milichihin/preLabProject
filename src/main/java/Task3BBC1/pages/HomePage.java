package Task3BBC1.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='module__title__link tag tag--news']")
    private WebElement newsChapter;



    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickOnNewsChapter() {
        newsChapter.click();
    }



}
