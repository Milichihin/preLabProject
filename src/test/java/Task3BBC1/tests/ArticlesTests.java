package Task3BBC1.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ArticlesTests extends BaseTest {

    private static final String TEST_ARTICLE = "Tonga says tsunami was 'unprecedented disaster'";
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

    @Test(priority = 1)
    public void compareOfMainArticle() {
        getHomePage().waitForPageLoadComplete(5);
        getHomePage().clickOnNewsChapter();
        getHomePage().waitForPageLoadComplete(5);
        assertEquals(getNewsPage().getTextFromActualMainArticle(), TEST_ARTICLE, "Your ArticleText is not equals expected");
    }

    @Test(priority = 2)
    public void compareRangeOfArticles() {

        getHomePage().waitForPageLoadComplete(5);
        getHomePage().clickOnNewsChapter();
        getHomePage().waitForPageLoadComplete(5);
        assertTrue(getNewsPage().isListOfArticlesEqual(RANGE_OF_TEST_ARTICLE));
    }
}
