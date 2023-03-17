package model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MainPage {

    //Логотип сервиса "Самокат"
    private static final By SERVICE_LOGO = By.className("Header_LogoScooter__3lsAR");
    //Кнопка "Да все привыкли"
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");
    //Список вопросов
    private static final By QUESTIONS_LIST = By.className("accordion");
    //Первый вопрос
    private static final By FIRST_QUESTION = By.id("accordion__heading-0");
    //Ответ на первый вопрос
    private static final By FIRST_ANSWER = By.xpath("//*[@id=\"accordion__panel-0\"]/p");
    //Последний вопрос
    private static final By LAST_QUESTION = By.id("accordion__heading-7");
    //Ответ на последний вопрос
    private static final By LAST_ANSWER = By.xpath("//*[@id=\"accordion__panel-7\"]/p");
    //Надпись на главной странице "Самокат на пару дней"
    private static final By HOME_HEADER = By.className("Home_Header__iJKdX");
    //Логотип Яндекса
    private static final By YANDEX_LOGO = By.cssSelector("[alt='Yandex']");
    //Кнопка "Статус заказа"
    private static final By ORDER_STATUS_BUTTON = By.className("Header_Link__1TAG7");
    //Плейсхолдер "Введите номер заказа"
    private static final By INPUT_ORDER_NUMBER = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //Go кнопка
    private static final By GO_BUTTON = By.xpath(".//button[text()='Go!']");
    //Изображение "Такого заказа нет"
    private static final By NOT_FOUND_IMAGE = By.cssSelector("div.Track_NotFound__6oaoY > img");


    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadServiceLogo() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(SERVICE_LOGO));
    }

    public void clickCookieButton() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    public void scrollToQuestionsList() {
        WebElement element = driver.findElement(QUESTIONS_LIST);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickFirstQuestion() {
        Assert.assertTrue(driver.findElement(FIRST_QUESTION).isEnabled());
        driver.findElement(FIRST_QUESTION).click();
    }

    public void verifyFirstAnswerText() {
        String firstAnswerText = driver.findElement(FIRST_ANSWER).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(FIRST_ANSWER, firstAnswerText));
    }

    public void clickLastQuestion() {
        Assert.assertTrue(driver.findElement(LAST_QUESTION).isEnabled());
        driver.findElement(LAST_QUESTION).click();
    }

    public void verifyLastAnswerText() {
        String lastAnswerText = driver.findElement(LAST_ANSWER).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(LAST_ANSWER, lastAnswerText));
    }

    public void clickServiceLogo() {
        driver.findElement(SERVICE_LOGO).click();
    }

    public boolean IsHomeHeaderDisplayed() {
        WebElement homeHeader =
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(HOME_HEADER));
        return homeHeader.isDisplayed();

    }

    public void clickYandexLogo() {
        String mainPage = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        driver.findElement(YANDEX_LOGO).click();
        new WebDriverWait(driver, 10).until(numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if(!mainPage.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        new WebDriverWait(driver, 10).until(titleIs("Дзен"));
    }

    public void clickOrderStatusButton() {
        driver.findElement(ORDER_STATUS_BUTTON).click();
    }

    public void waitForLoadOrderNumberInput() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(INPUT_ORDER_NUMBER));
    }

    public void enterOrderNumber(String orderNumber) {
        driver.findElement(INPUT_ORDER_NUMBER).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(GO_BUTTON).click();
    }

    public boolean isImageNotFoundDisplayed() {
        WebElement notFoundImage =
                new WebDriverWait(driver, 10).
                        until(ExpectedConditions.visibilityOfElementLocated(NOT_FOUND_IMAGE));
        return notFoundImage.isDisplayed();
    }

    private static final By SEARCH_INPUT = By.xpath(".//input[1]");

    public boolean isSearchInputDisplayed() {
        WebElement searchInput =
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        return searchInput.isDisplayed();
    }
}

