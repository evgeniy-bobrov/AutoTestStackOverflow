import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class AutoTest {
    static WebDriver driver;
    public static final String SEARCH_WORD = "webdriver";

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

// 1 task
        goToWebSiteStackoverflow();
// 2 task
        StartPage startPage = new StartPage();
        startPage.fillFieldSearch();
        startPage.clickButtonSearch();
// 3 task
        ResultSearchPage resultSearch = new ResultSearchPage();
        resultSearch.clickButtonResult50();
        resultSearch.checkListResultSearch();
        resultSearch.clickButtonGoToFirstPage();
// 4 task
        resultSearch.checkHeadersInListResultSearch();
// 5 task
        resultSearch.clickLinkTags();
// 6 task
        TagsPage tagsPage = new TagsPage();
        tagsPage.fillFieldSearchTags();
        tagsPage.checkResultTags();
// 7 task
        tagsPage.clickCoincideTagsResult();
        ResultSearchTagsPage resultSearchTagsPage = new ResultSearchTagsPage();
        resultSearchTagsPage.checkPresenceTag();
    }
    @AfterClass
    public static void close() {driver.quit();}

    @Step
    public void goToWebSiteStackoverflow() {
        driver.get("http://stackoverflow.com/");
    }
}
