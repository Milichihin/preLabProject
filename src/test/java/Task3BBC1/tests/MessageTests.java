package Task3BBC1.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class MessageTests extends BaseTest {


    public List<WebElement> elements () {
        List<WebElement> el = new ArrayList<WebElement>();
        el.add(getMessagePage().getPhoneField());
        el.add(getMessagePage().getLocationField());
        el.add(getMessagePage().getAgeField());
        el.add(getMessagePage().getQuestionField());
        el.add(getMessagePage().getNameField());
        el.add(getMessagePage().getEmailField());
        return el;
    }


    @Test
    public void verifyForEmptyField() {

        getHomePage().waitForPageLoadComplete(5);
        getHomePage().clickOnNewsChapter();

        if (getModalPage().getModalWindow() != null) {
            getHomePage().implicitWait(5);
            getModalPage().clickOnCloseModalWindowButton();
            getHomePage().implicitWait(5);
        }

        getNewsPage().clickOnCoronavirusChapter();
        getHomePage().waitForPageLoadComplete(5);
        getCoronavirusPage().clickOnCoronavirusStories();


        getHomePage().waitForPageLoadComplete(5);
        getCoronavirusPage().clickOnQuestion();
        getHomePage().waitForPageLoadComplete(15);

        getMessagePage().fillTheFields(getMessagePage().getQuestionField(), "", elements(), "qwerty");
        assertTrue(getMessagePage().isError());

        getMessagePage().fillTheFields(getMessagePage().getNameField(), "", elements(), "qwerty");
        assertTrue(getMessagePage().isError());

        getMessagePage().fillTheFields(getMessagePage().getEmailField(), "", elements(), "qwerty");
        assertTrue(getMessagePage().isError());
    }

}
