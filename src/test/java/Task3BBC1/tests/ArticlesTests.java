package Task3BBC1.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ArticlesTests extends BaseTest {

    private static final String TEST_ARTICLE = "No new England Covid rules before new year";
    private static final List<String> RANGE = Arrays.asList(
            "Omicron and Delta driving case tsunami - WHO",
            "",
            "Public shaming returns in China amid Covid fears",
            "Hong Kong news website shuts as staff arrested",
            "How is it going with those grand COP26 commitments?",
            "Desmond Tutu's daughter: 'Daddy was a hugger'",
            "'Icemageddon' warnings follow Alaska heat record",
            "Legend of street photography Sabine Weiss dies at 97",
            "Russia orders health and drug checks for foreigners",
            "Teen shot by police in LA died in mother's arms",
            "TikTok moderator sues over 'psychological trauma'",
            "",
            "Hurricanes to expand into more populated regions",
            "Citroën Egypt ad accused of normalising harassment",
            "Uganda detains award-winning author",
            "Three die after memorial candle sparks hospital fire"
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
