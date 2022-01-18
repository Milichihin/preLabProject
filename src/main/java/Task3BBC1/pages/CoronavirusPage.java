package Task3BBC1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoronavirusPage extends BasePage {

    @FindBy(xpath = "//ul[@class='gs-o-list-ui--top-no-border nw-c-nav__secondary-sections'] //span[contains(text(),'Your Coronavirus Stories')]")
    private WebElement coronavirusStories;

    @FindBy(xpath = "//a[@href='/news/52143212']")
    private WebElement question;


    public CoronavirusPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCoronavirusStories() {
        coronavirusStories.click();
    }

    public void clickOnQuestion() {
        question.click();
    }
}
