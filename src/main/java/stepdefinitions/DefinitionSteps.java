package stepdefinitions;

import Task3BBC1.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 60;


    private static final List<String> RANGE = Arrays.asList(
            "Tonga says tsunami was 'unprecedented disaster'",
            "Tonga says tsunami was 'unprecedented disaster'",
            "Tonga tsunami: Before and after eruption",
            "Nobody told me drinks event broke rules - Johnson",
            "Saudis warned of jail time for posting rumours",
            "'World's most valuable house' - which no one wanted to buy",
            "Backlash as US billionaire dismisses Uyghur abuse",
            "Mobile firms agree another 5G delay at US airports",
            "Irish police arrest man over Ashling Murphy killing",
            "Microsoft plans to buy Activision Blizzard",
            "Buffy director Whedon denies misconduct claims",
            "Nigeria shoot-to-kill jail break order issued",
            "Buffy director Whedon denies misconduct claims",
            "Nigeria shoot-to-kill jail break order issued",
            "UK teens held over Texas synagogue siege released",
            "Youngest ever president elected by EU Parliament",
            "Beijing Olympics athletes told to use burner phones"
    );
    private static final ArrayList<String> RANGE_OF_TEST_ARTICLE = new ArrayList<>(RANGE);
//
//    public List<WebElement> ELEMENTS () {
//        List<WebElement> el = new ArrayList<WebElement>();
//        el.add(messagePage.getPhoneField());
//        el.add(messagePage.getLocationField());
//        el.add(messagePage.getAgeField());
//        el.add(messagePage.getQuestionField());
//        el.add(messagePage.getNameField());
//        el.add(messagePage.getEmailField());
//        return el;
//    }

    WebDriver driver;
    HomePage homePage;
    CoronavirusPage coronavirusPage;
    MessagePage messagePage;
    ModalPage modalPage;
    NewsPage newsPage;
    SearchPage searchPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens HomePage")
    public void userOpensHomePage() {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage("https://www.bbc.com/");
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }


    @And("User Clicks on News")
    public void userClicksOnNews() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickOnNewsChapter();
    }

    @And("User checks compliance the name of the headline article to {string} article")
    public void userChecksComplianceTheNameOfTheHeadlineArticleToTestedHeadlineArticleArticle(final String article) {
        newsPage = pageFactoryManager.getNewsPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(newsPage.getTextFromActualMainArticle(), article);
    }

    @And("Checks secondary article titles")
    public void checksSecondaryArticleTitles() {
        newsPage = pageFactoryManager.getNewsPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(newsPage.isListOfArticlesEqual(RANGE_OF_TEST_ARTICLE));
    }

    @And("User found a category name of headline article")
    public void userFoundACategoryNameOfHeadlineArticle() {
        newsPage = pageFactoryManager.getNewsPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        newsPage.isNameOfFirstCategoryVisible();
    }

    @And("User makes search by name of headline article category")
    public void userMakesSearchByNameOfHeadlineArticle() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        newsPage.fillTheSearchField(newsPage.getStringOfNameOfFirstCategory());
        newsPage.clickOnSearchButton();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("Check the name of the first article against a name of headline article")
    public void checkTheNameOfTheFirstArticleAgainstANameOfHeadlineArticle() {
        searchPage = pageFactoryManager.getSearchPage();
        newsPage = pageFactoryManager.getNewsPage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.firstFoundedArticleElement);
        assertTrue(searchPage.getFirstFoundArticleText().contains("Asia"));
    }

    @And("User closes modal window if it showing")
    public void userClosesModalWindowIfItShowing() {
        modalPage = pageFactoryManager.getModalPage();
        if (modalPage.getModalWindow() != null) {
            homePage.implicitWait(5);
            modalPage.clickOnCloseModalWindowButton();
            homePage.implicitWait(5);
        }
    }

    @And("User clicks on `Coronavirus`")
    public void userClicksOnCoronavirus() {
        newsPage = pageFactoryManager.getNewsPage();
        newsPage.clickOnCoronavirusChapter();
    }

    @Then("User clicks on `Your Coronavirus Stories`")
    public void userClicksOnYourCoronavirusStories() {
        coronavirusPage = pageFactoryManager.getCoronavirusPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        coronavirusPage.clickOnCoronavirusStories();
    }

    @And("User goes to `Coronavirus: Send us your questions`")
    public void userGoesToCoronavirusSendUsYourQuestions() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        coronavirusPage.clickOnQuestion();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @Then("User fills the {string} data into the one {string} and {string} data into the others")
    public void userFillsTheEmptyDataDataIntoTheOneFieldAndCorrectDataDataIntoTheOthers(final String emptyData, final String field, final String correctData) {
        messagePage = pageFactoryManager.getMessagePage();
        messagePage.fillTheMultiFields(field, emptyData, messagePage.getElements(), correctData);
    }

    @Then("User checks does the form react correctly to errors")
    public void userChecksDoesTheFormReactCorrectlyToErrors() {
        assertTrue(messagePage.isError());
    }









    @After
    public void tearDown() {
        driver.close();
    }


}
