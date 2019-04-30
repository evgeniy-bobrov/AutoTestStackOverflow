import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;




public class StartPage {

    private WebElement fieldSearch = AutoTest.driver.findElement(By.xpath("//*[@id=\"search\"]/div/input"));

    private WebElement buttonSearch = AutoTest.driver.findElement(By.xpath("//*[@id=\"search\"]/div/button"));

    // вводим в поле поиска необходимое нам слово
    @Step
    public void fillFieldSearch(){
        fieldSearch.sendKeys(AutoTest.SEARCH_WORD);
    }

    // нажимаем кнопку поиска (лупа)
    @Step
    public void clickButtonSearch (){
        buttonSearch.click();
    }

}
