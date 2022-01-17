package Task3BBC1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalPage extends BasePage {


    @FindBy(xpath = "//button[@class='tp-close tp-active']")
    public WebElement closeModalWindowButton;

    @FindBy(xpath = "//div[@class='tp-modal']")
    public WebElement modalWindow;
    

    public ModalPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCloseModalWindowButton(){
        return closeModalWindowButton;
    }

    public WebElement getModalWindow(){
        return modalWindow;
    }

    public void clickOnCloseModalWindowButton(){
        closeModalWindowButton.click();
    }


}
