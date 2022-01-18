package manager;
import org.openqa.selenium.WebDriver;
import Task3BBC1.pages.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() { return new HomePage(driver); }

    public CoronavirusPage getCoronavirusPage() { return new CoronavirusPage(driver); }

    public MessagePage getMessagePage() { return new MessagePage(driver); }

    public ModalPage getModalPage() { return new ModalPage(driver); }

    public NewsPage getNewsPage() { return new NewsPage(driver); }

    public SearchPage getSearchPage() { return new SearchPage(driver); }


}
