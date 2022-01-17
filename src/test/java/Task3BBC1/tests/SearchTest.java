package Task3BBC1.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest {

    private static final String TEXT_FROM_TOP_STORY = "World";

    @Test
    public void compareWordInSearch() {

        getHomePage().clickOnNewsChapter();
        getHomePage().waitForPageLoadComplete(5);
        getNewsPage().fillTheSearchField(TEXT_FROM_TOP_STORY);
        getNewsPage().clickOnSearchButton();
        getSearchPage().waitVisibilityOfElement(10, getSearchPage().firstFoundedArticleElement);
        assertTrue(getSearchPage().getFirstFoundArticleText().contains(TEXT_FROM_TOP_STORY));
    }
}
