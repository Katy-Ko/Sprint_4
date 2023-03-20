package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MainPage {

    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //Логотип сервиса "Самокат"
    private static final By SERVICE_LOGO = By.className("Header_LogoScooter__3lsAR");
    //Кнопка "Да все привыкли"
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");
    //Список вопросов
    private static final By FAQ_LIST = By.className("accordion");
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
        WebElement element = driver.findElement(FAQ_LIST);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickQuestion(String question) {
        String questionsList = String.format(".//div[@class='accordion']//*[text()='%s']", question);
        driver.findElement(By.xpath(questionsList)).click();
    }

    public void verifyAnswer(String answer) {
        String answersList = String.format(".//div[@class='accordion']//*[text()='%s']", answer);
        String answerText = driver.findElement(By.xpath(answersList)).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(answersList), answerText));
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

}

