package Task3BBC1.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class MessagePage extends BasePage {

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement acceptCheckBox;

    @FindBy(xpath = "//textarea")
    public WebElement questionField;

    @FindBy(xpath = "//input[@aria-label='Name']")
    public WebElement nameField;

    @FindBy(xpath = "//input[@aria-label='Email address']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@aria-label='Contact number']")
    public WebElement phoneField;

    @FindBy(xpath = "//input[@aria-label='Location ']")
    public WebElement locationField;

    @FindBy(xpath = "//input[@aria-label='Age']")
    public WebElement ageField;

    @FindBy(xpath = "//button[@class='button']")
    public WebElement submitButton;

    @FindBy(xpath = "//button[@class='tp-close tp-active']")
    public WebElement closeModalWindowButton;

    @FindBy(xpath = "//div[@class='tp-modal']")
    public WebElement modalWindow;

    @FindBy(xpath = "//div[@class='input-error-message']")
    public List<WebElement> errorFields;

    public MessagePage(WebDriver driver) {
        super(driver);
    }


    public WebElement getQuestionField() {
        return questionField;
    }


    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPhoneField() {
        return phoneField;
    }

    public WebElement getLocationField() {
        return locationField;
    }

    public WebElement getAgeField() {
        return ageField;
    }

    public void clickOnAcceptCheckBox() {
        acceptCheckBox.click();
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }

    public boolean isError() {
        return errorFields.size() > 0;
    }

    public void fillTheFields(WebElement emptyElem, String empty, List<WebElement> elemList, String qwerty) {
        for (int i = 0; i < elemList.size(); i++) {
            elemList.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            elemList.get(i).sendKeys(qwerty);
        }
        emptyElem.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emptyElem.sendKeys(empty);
        clickOnAcceptCheckBox();
        clickOnSubmitButton();
        implicitWait(5);
    }
}
