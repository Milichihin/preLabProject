package Task3BBC1.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ArticlesTests extends BaseTest {

    private static final String TEST_ARTICLE = "Ash hampers Tonga aid as scale of damage emerges";
    private static final List<String> RANGE = Arrays.asList(
            "Ash hampers Tonga aid as scale of damage emerges",
            "",
            "'If I find a body, I recover a piece of my son'",
            "Rabbi threw chair to escape Texas synagogue gunman",
            "UK says it is sending weapons to defend Ukraine",
            "HK police charge former aircrew over Covid rules",
            "Body of missing four-year-old Belgian boy found",
            "Afghanistan twin quakes kill at least 22",
            "Suspect identified over Anne Frank's betrayal",
            "Plastic crisis needs binding treaty, report says",
            "French far-right candidate guilty of hate speech",
            "",
            "",
            "BBC licence fee to be frozen at £159 for two years",
            "More MI5 alerts to come to counter foreign interference",
            "Australian Open: Murray v Basilashvili - radio & text",
            "UK job vacancies hit new high after furlough ends"
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
