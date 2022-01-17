package Task3BBC1.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsPage extends BasePage {

    @FindBy(xpath = "//h3[@class='gs-c-promo-heading__title gel-paragon-bold nw-o-link-split__text']")
    private WebElement actualMainArticle;

    @FindBy(xpath = "//div[@class='gel-wrap gs-u-pt+']//h3")
    private List<WebElement> arrayOfActualArticlesElements;

    @FindBy(xpath = "//input[@id='orb-search-q']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@id='orb-search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@class='gs-o-list-ui--top-no-border nw-c-nav__wide-sections'] // span[contains(text(),'Coronavirus')]")
    private WebElement coronavirusChapter;

    public NewsPage(WebDriver driver) {
        super(driver);
    }


    public String getTextFromActualMainArticle() {
        return actualMainArticle.getText();
    }

    public ArrayList<String> getListOfActualArticlesStrings() {
        ArrayList<String> arrOfArticles = new ArrayList<>();
        for (WebElement el : arrayOfActualArticlesElements) {
            arrOfArticles.add(el.getText());
        }
        return arrOfArticles;
    }

    public boolean isListOfArticlesEqual(@NotNull List<String> testArticles) {
        boolean check = false;
        testArticles.removeAll(getListOfActualArticlesStrings());
        if (testArticles.size() == 0) check = true;
        return check;
    }

    public void fillTheSearchField(String text) {
        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }

    public void clickOnCoronavirusChapter() {
        coronavirusChapter.click();
    }


}
