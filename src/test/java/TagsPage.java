import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class TagsPage {

    private WebElement fieldSearchTags = AutoTest.driver.findElement(By.xpath("//*[@id=\"tagfilter\"]"));

    private List<WebElement> tagsResult = AutoTest.driver.findElements(By.xpath("//*[@id=\"tags-browser\"]/div/a"));

    // вводим в поле поиска по тэгам слово для поиска
    @Step
    public void fillFieldSearchTags(){
        fieldSearchTags.sendKeys(AutoTest.SEARCH_WORD);
    }

    // проверяем, что вышли результаты по тэгам с заданным нами словом
    @Step
    public void checkResultTags() throws InterruptedException {
        Thread.sleep(1000);
        tagsResult = AutoTest.driver.findElements(By.xpath("//*[@id=\"tags-browser\"]/div/a"));
        for (WebElement name : tagsResult){
            Assert.assertTrue(name.getText().replaceAll("-","").contains(AutoTest.SEARCH_WORD));
        }
    }

    // кликаем на результат по точному совпадению
    @Step
    public void clickCoincideTagsResult (){
        tagsResult = AutoTest.driver.findElements(By.xpath("//*[@id=\"tags-browser\"]/div/a"));
        for (WebElement name : tagsResult){
            if (name.getText().equals(AutoTest.SEARCH_WORD)){
                name.click();
                break;
            }
        }
    }
}
