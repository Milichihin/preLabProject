package Task3BBC1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchPage extends BasePage {

    @FindBy(xpath = "//p[1]")
    public WebElement firstFoundedArticleElement;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstFoundArticleText(){
        return firstFoundedArticleElement.getText();
    }


}
