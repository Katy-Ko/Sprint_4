package model;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    //Кнопка "Заказать" в верхней части страницы
    private static final By ORDER_BUTTON_TOP = By.className("Button_Button__ra12g");
    //Текст "Для кого самокат" на странице с формой заказа
    private static final By ORDER_HEADER = By.className("Order_Header__BZXOb");
    //Поле "Имя"
    private static final By INPUT_NAME = By.xpath(".//input[@placeholder='* Имя']");
    //Поле "Фамилия".
    private static final By INPUT_LAST_NAME = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле "Адрес"
    private static final By INPUT_ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Селектор "Станция метро"
    private static final By INPUT_METRO_STATION = By.className("select-search__input");
    //Поле "Телефон"
    private static final By INPUT_PHONE_NUMBER = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    //Поле "Когда привезти самокат"
    private static final By RENT_DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Стрелка, раскрывающая список периодов аренды
    private static final By DROPDOWN_ARROW = By.className("Dropdown-arrow");
    //Поле "Комментарий для курьера"
    private static final By COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать" под формой "Про аренду"
    private static final By ORDER_BUTTON_UNDER_FOR_WHOM_FORM = By.xpath(".//button[2][text()='Заказать']");
    //Кнопка "Да" в модалке с вопросом "Хотите оформить заказ?"
    private static final By YES_BUTTON = By.xpath(".//button[2][text()='Да']");
    //Текст в модалке "Заказ оформлен"
    private static final By ORDER_CREATED_STATUS = By.xpath(".//div[@class='Order_Modal__YZ-d3']//*[text()='Заказ оформлен']");
    //Кнопка "Заказать" в нижней части страницы
    private static final By ORDER_BUTTON_BOTTOM = By.xpath(".//div[5]/button");


    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        Assert.assertTrue(driver.findElement(ORDER_BUTTON_TOP).isEnabled());
        driver.findElement(ORDER_BUTTON_TOP).click();
    }

    public void waitForLoadOrderHeader() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_HEADER));
    }

    public void selectMetro(String metro) {
        driver.findElement(INPUT_METRO_STATION).sendKeys(metro);
        selectMetroFromOptions(metro);
    }

    private void selectMetroFromOptions(String metro) {
        String metroOptionTemplate = String.format(".//div[@class='select-search__select']//*[text()='%s']", metro);
        driver.findElement(By.xpath(metroOptionTemplate)).click();
    }

    public void setOrderDetails
            (String name, String lastName, String address, String metro, String phoneNumber,
                    String date, String period, String color, String comment) {
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
        driver.findElement(INPUT_ADDRESS).sendKeys(address);
        selectMetro(metro);
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phoneNumber);
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys(date);
        selectPeriod(period);
        selectColor(color);
        driver.findElement(COMMENT).sendKeys(comment);
        driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).click();
        driver.findElement(YES_BUTTON).click();
    }

    public void waitForChangedHeader() {
        String changed = driver.findElement(ORDER_HEADER).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(ORDER_HEADER, changed));
    }

    public void selectPeriod(String period) {
        driver.findElement(DROPDOWN_ARROW).click();
        String periodMenu = String.format(".//div[@class='Dropdown-menu']//*[text()='%s']", period);
        driver.findElement(By.xpath(periodMenu)).click();
    }

    public void selectColor(String color) {
        String scooterColor = String.format(".//div[@class='Order_Checkboxes__3lWSI']//*[text()='%s']", color);
        driver.findElement(By.xpath(scooterColor)).click();
    }

    public boolean isOrderCreatedStatusDisplayed() {
        WebElement initialStatus =
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(ORDER_CREATED_STATUS));
        return initialStatus.isDisplayed();
    }

    public void scrollToOrderButtonBottom() {
        WebElement element = driver.findElement(ORDER_BUTTON_BOTTOM);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOrderButtonBottom() {
        Assert.assertTrue(driver.findElement(ORDER_BUTTON_BOTTOM).isEnabled());
        driver.findElement(ORDER_BUTTON_BOTTOM).click();
    }

    public void fillOutPersonalData(String name, String lastName, String address, String metro, String phoneNumber) {
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
        driver.findElement(INPUT_ADDRESS).sendKeys(address);
        driver.findElement(INPUT_METRO_STATION).sendKeys(metro);
        selectMetro(metro);
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phoneNumber);
        driver.findElement(NEXT_BUTTON).click();
    }

    public boolean isErrorTextDisplayed(String error) {
        String personalDataForm = String.format(".//div[@class='Order_Form__17u6u']//*[text()='%s']", error);
        WebElement errorText =
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(personalDataForm)));
        return errorText.isDisplayed();
    }
}

