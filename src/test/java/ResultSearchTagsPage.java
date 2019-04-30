import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ResultSearchTagsPage {

    private List<WebElement> listElements = AutoTest.driver.findElements(By.xpath("//div[@id='questions']/div[@class='question-summary']"));

    private List<WebElement> listTagsInElement = AutoTest.driver.findElements(By.xpath("//div[@id='questions']/div[@class='question-summary']/div/div/a"));

    private List<WebElement> buttonNext = AutoTest.driver.findElements(By.xpath("//*[@id=\"mainbar\"]/div/a[@rel='next']/span"));

    // проверяем что в у каждого результата есть тэг который мы выбрали
    @Step
    public void checkPresenceTag () throws InterruptedException {
        while (buttonNext.size() > 0){
            listElements = AutoTest.driver.findElements(By.xpath("//div[@id='questions']/div[@class='question-summary']"));
            buttonNext = AutoTest.driver.findElements(By.xpath("//*[@id=\"mainbar\"]/div/a[@rel='next']/span"));
            for (WebElement element : listElements){
                Thread.sleep(1000);
                listTagsInElement = element.findElements(By.xpath("./div/div/a"));
                boolean flag = false;
                for (WebElement tags : listTagsInElement){
                    if (tags.getText().equals(AutoTest.SEARCH_WORD)){
                        flag = true;
                    }
                }
                Assert.assertTrue(flag);
            }
            if (buttonNext.size() > 0) {
                ((JavascriptExecutor) AutoTest.driver).executeScript("arguments[0].scrollIntoView(true);", buttonNext.get(0));
                buttonNext.get(0).click();
            } else break;
        }
    }
}
