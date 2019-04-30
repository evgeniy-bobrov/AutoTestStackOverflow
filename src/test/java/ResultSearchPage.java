import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;


public class ResultSearchPage {

    private WebElement buttonResult50 = AutoTest.driver.findElement(By.xpath("//div[6]/a[3]"));

    private List<WebElement> listResultSearch = AutoTest.driver.findElements(By.xpath("//div[2]/div[1]/h3/a"));

    private List<WebElement> buttonNext = AutoTest.driver.findElements(By.xpath("//*[@id=\"mainbar\"]/div/a[@rel='next']/span"));

    // кликаем на кнопку "50" для вывода на странице поиска по 50 результатов на страницу
    @Step
    public void clickButtonResult50() throws InterruptedException {
        ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", buttonResult50);
        buttonResult50.click();
        listResultSearch = AutoTest.driver.findElements(By.xpath("//div[2]/div[1]/h3/a"));
    }

    // проверяем что каждый заголовок выведенного списка результатов содержит слово, которое мы вводили для поиска
    @Step
    public void checkListResultSearch() throws InterruptedException {
        while (buttonNext.size() > 0) {
            listResultSearch = AutoTest.driver.findElements(By.xpath("//div[2]/div[1]/h3/a"));
            buttonNext = AutoTest.driver.findElements(By.xpath("//*[@id=\"mainbar\"]/div/a[@rel='next']/span"));
            for (WebElement name : listResultSearch) {
                Thread.sleep(1000);
                try {
                    Assert.assertTrue(name.getText().toLowerCase().contains(AutoTest.SEARCH_WORD));
                } catch (java.lang.AssertionError e) {
                    //System.out.println("Исключения: заголовок не содержит слово '" + AutoTest.SEARCH_WORD + "' - \"" + name.getText() + "\""); // эта команда для запуска без Allure отчета, для отображения ошибки в консоли
                    Allure.getLifecycle().startStep(randomUUID().toString(), new StepResult()
                            .withName("Exceptions: the title does not contain the word '" + AutoTest.SEARCH_WORD + "' - \"" + name.getText() +
                                    "\"")
                            .withStatus(Status.FAILED));
                    Allure.getLifecycle().stopStep();
                }
            }
            if (buttonNext.size() > 0) {
                ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", buttonNext.get(0));
                buttonNext.get(0).click();
            } else break;
        }
    }

    // переход на первую страницу в списке результатов поиска
    @Step
    public void clickButtonGoToFirstPage() {
        WebElement firstResultPage = AutoTest.driver.findElement(By.xpath("//*[@id=\"mainbar\"]/div[5]/a[2]/span"));
        ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", firstResultPage);
        firstResultPage.click();
    }

    // входим в каждое обсуждения из списка результатов и проверяем, что перешли именно в эту тему (проверяем заголовок обсуждения).
    @Step
    public void checkHeadersInListResultSearch() throws InterruptedException {
        int numberPage = 1;
        buttonNext = AutoTest.driver.findElements(By.xpath("//*[@id=\"mainbar\"]/div/a[@rel='next']/span"));
        while (buttonNext.size() > 0) {
            listResultSearch = AutoTest.driver.findElements(By.xpath("//div[2]/div[1]/h3/a"));
            List<String> listStringHeaders = new ArrayList<String>();
            for (WebElement name : listResultSearch) {
                Thread.sleep(1000);
                listStringHeaders.add(name.getText());
            }
            int numberResultOnPage = 1;
            for (String nameHeader : listStringHeaders) {
                listResultSearch = AutoTest.driver.findElements(By.xpath("//div[2]/div[1]/h3/a"));
                WebElement headerWebElement = AutoTest.driver.findElement(By.linkText(nameHeader));
                String fullNameHeaderInTitle = headerWebElement.getAttribute("title").trim();
                ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", headerWebElement);
                if (numberPage == 1){ // только на первой странице вылезает маленькое синее окно, которое в последствии мешает кликнуть на ссылку, поэтому это окно нужно сначала закрыть
                    List<WebElement> closeButtonSideBar = AutoTest.driver.findElements(By.xpath("//*[@id=\"left-sidebar\"]/div/nav/ol/li[4]/ol/li[2]/div/p"));
                    Thread.sleep(800);
                    if (closeButtonSideBar.size() > 0 && closeButtonSideBar.get(0).isDisplayed()) {
                        closeButtonSideBar.get(0).click();
                        Thread.sleep(900);
                    }
                }
                headerWebElement.click();
                WebElement headerWebElementQuestionPage = AutoTest.driver.findElement(By.xpath("//*[@id=\"question-header\"]/h1/a"));
                String nameHeaderInQuestionPage = headerWebElementQuestionPage.getText();
                try {
                    Assert.assertTrue(nameHeaderInQuestionPage.replaceAll("“", "\"").replaceAll("”", "\"").contains(fullNameHeaderInTitle));
                } catch (java.lang.AssertionError e) {
                    //System.out.println("Исключения: заголовок по счету " + numberResultOnPage + ", на странице № " + numberPage + " из списка в поиске: " + fullNameHeaderInTitle + ", не соответсвует заголовку внутри Вопроса: " + nameHeaderInQuestionPage); // эта команда для запуска без Allure отчета, для отображения ошибки в консоли
                    Allure.getLifecycle().startStep(randomUUID().toString(), new StepResult()
                            .withName("Exception: the title of the account "+ numberResultOnPage + ", on page Number - " + numberPage + " from the list in the search: '" + fullNameHeaderInTitle + "', does not match the title inside the Question: '" + nameHeaderInQuestionPage + "'")
                            .withStatus(Status.FAILED));
                    Allure.getLifecycle().stopStep();
                }
                AutoTest.driver.navigate().back();
                numberResultOnPage++;
            }
            numberPage++;
            buttonNext = AutoTest.driver.findElements(By.xpath("//*[@id=\"mainbar\"]/div/a[@rel='next']/span"));
            if (buttonNext.size() > 0) {
                ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", buttonNext.get(0));
                buttonNext.get(0).click();
            } else break;
        }
    }

    // нажимаем на ссылку Тэги
    @Step
    public void clickLinkTags() throws InterruptedException {
        Thread.sleep(1000);
        WebElement linkTags = AutoTest.driver.findElement(By.xpath("//*[@id=\"nav-tags\"]"));
        ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", linkTags);
        linkTags.click();
    }
}
