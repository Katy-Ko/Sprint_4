package model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class YandexPage {

    private WebDriver driver;

    public YandexPage(WebDriver driver) {
        this.driver = driver;
    }

    //Поисковая строка Яндекс
    private static final By SEARCH_INPUT = By.xpath(".//input[1]");

    public boolean isSearchInputDisplayed() {
        WebElement searchInput =
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        return searchInput.isDisplayed();
    }
}
