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
    //Поле "Фамилия".//div[1][text()='Для кого самокат']
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
    //Сутки аренды
    private static final By ONE_DAY_RENT = By.xpath("//div[1][text()='сутки']");
    //Четверо суток аренды
    private static final By FOUR_DAYS_RENT = By.xpath("//div[4][text()='четверо суток']");

    //Чекбокс "чёрный жемчуг"
    private static final By CHECKBOX_BLACK = By.id("black");
    //Чекбокс "серая бузысходность"
    private static final By CHECKBOX_GREY = By.id("grey");
    //Поле "Комментарий для курьера"
    private static final By COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать" под формой "Про аренду"
    private static final By ORDER_BUTTON_UNDER_FOR_WHOM_FORM = By.xpath(".//button[2][text()='Заказать']");
    //Кнопка "Да" в модалке с вопросом "Хотите оформить заказ?"
    private static final By YES_BUTTON = By.xpath(".//button[2][text()='Да']");
    //Кнопка "Посмотреть статус" после завершения оформления
    private static final By CHECK_STATUS_BUTTON = By.xpath(".//button[text()='Посмотреть статус']");
    //Начальный статус "Самокат на складе"
    private static final By INITIAL_STATUS = By.xpath(".//div[1][text()='Самокат на складе']");
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


    public void setOneDayRent_OrderDetails
            (String name, String lastName, String address, String metro, String phoneNumber, String date, String comment) {
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
        driver.findElement(INPUT_ADDRESS).sendKeys(address);
        selectMetro(metro);
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phoneNumber);
        Assert.assertTrue(driver.findElement(NEXT_BUTTON).isEnabled());
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys(date);
        driver.findElement(DROPDOWN_ARROW).click();
        driver.findElement(ONE_DAY_RENT).click();
        Assert.assertTrue(driver.findElement(CHECKBOX_GREY).isEnabled());
        driver.findElement(CHECKBOX_GREY).click();
        driver.findElement(COMMENT).sendKeys(comment);
        Assert.assertTrue(driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).isEnabled());
        driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).click();
        Assert.assertTrue(driver.findElement(YES_BUTTON).isEnabled());
        driver.findElement(YES_BUTTON).click();
    }

    public void waitForChangedHeader() {
        String changed = driver.findElement(ORDER_HEADER).getText();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(ORDER_HEADER, changed));
    }

    public void waitForLoadCheckStatusButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(CHECK_STATUS_BUTTON));
        driver.findElement(CHECK_STATUS_BUTTON).click();
    }

    public boolean isInitialStatusDisplayed() {
        WebElement initialStatus =
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(INITIAL_STATUS));
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

    public void setFourDaysRent_OrderDetails
            (String name, String lastName, String address, String metro, String phoneNumber, String date, String comment) {
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
        driver.findElement(INPUT_ADDRESS).sendKeys(address);
        selectMetro(metro);
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phoneNumber);
        Assert.assertTrue(driver.findElement(NEXT_BUTTON).isEnabled());
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys(date);
        driver.findElement(DROPDOWN_ARROW).click();
        driver.findElement(FOUR_DAYS_RENT).click();
        Assert.assertTrue(driver.findElement(CHECKBOX_BLACK).isEnabled());
        driver.findElement(CHECKBOX_BLACK).click();
        driver.findElement(COMMENT).sendKeys(comment);
        Assert.assertTrue(driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).isEnabled());
        driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).click();
        Assert.assertTrue(driver.findElement(YES_BUTTON).isEnabled());
        driver.findElement(YES_BUTTON).click();
    }

    /*В MozillaFirefox тесты иногда падали на моменте появления модального окна с номером заказа, где мы нажимаем
    "Посмотреть статус". Браузер мог ввести несущестующий номер заказа или вообще оставить поле ввода пустым, и тогда
    появвлялось изображение с тектом "Заказ не найден". Не совсем понятно, как предотвратить такое поведение.
    Пробовала вставлять ожидание повления текста с подтверждением в модальном окне, а не кнопки "Посмотреть статус",
    как сейчас. Но проблему не решило.
    Некоторые тексты всё равно падали из-за некорректно введённого номера заказа.
     */

    public void fillOutPersonalData_withCompoundName() {
        driver.findElement(INPUT_NAME).sendKeys("Анна-Мария");
        driver.findElement(INPUT_LAST_NAME).sendKeys("Попова");
        driver.findElement(INPUT_ADDRESS).sendKeys("ул.Ватутина, 20");
        selectMetro("Черкизовская");
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys("+79995551212");
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys("01.04.2023");
        //Не очень понятно, почему тест проходит, если не включать последний шаг с заполнением даты.
        //То есть, несмотря на "неправильно" заполненное поле, кнопка "Далее" будто отрабатывает,
        // но не переводит на следующую страницу
    }

    public void fillOutPersonalData_withCompoundLastName() {
        driver.findElement(INPUT_NAME).sendKeys("Николай");
        driver.findElement(INPUT_LAST_NAME).sendKeys("Римский-Корсаков");
        driver.findElement(INPUT_NAME).click();
        driver.findElement(INPUT_ADDRESS).sendKeys("ул.Ватутина, 20");
        selectMetro("Черкизовская");
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys("+79995551212");
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys("01.04.2023");
    }

    public void fillOutPersonalData_withNonexistentAddress() {
        driver.findElement(INPUT_NAME).sendKeys("Петя");
        driver.findElement(INPUT_LAST_NAME).sendKeys("Батарейкин");
        driver.findElement(INPUT_ADDRESS).sendKeys("Привезите сюда");
        selectMetro("Черкизовская");
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys("+79995551212");
        driver.findElement(NEXT_BUTTON).click();
    }

    public void fillOutPersonalData_withNonexistentPhone() {
        driver.findElement(INPUT_NAME).sendKeys("Петя");
        driver.findElement(INPUT_LAST_NAME).sendKeys("Батарейкин");
        driver.findElement(INPUT_ADDRESS).sendKeys("ул. Попова");
        selectMetro("Черкизовская");
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys("55555555555");
        driver.findElement(NEXT_BUTTON).click();
    }

    public void fillOutOrderForm_withPastDate() {
        driver.findElement(INPUT_NAME).sendKeys("Петя");
        driver.findElement(INPUT_LAST_NAME).sendKeys("Батарейкин");
        driver.findElement(INPUT_ADDRESS).sendKeys("ул. Попова");
        selectMetro("Черкизовская");
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys("+79215552121");
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys("01.02.2023");
        driver.findElement(DROPDOWN_ARROW).click();
        driver.findElement(FOUR_DAYS_RENT).click();
        Assert.assertTrue(driver.findElement(CHECKBOX_BLACK).isEnabled());
        driver.findElement(CHECKBOX_BLACK).click();
        driver.findElement(COMMENT).sendKeys(" ");
        Assert.assertTrue(driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).isEnabled());
        driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).click();
        Assert.assertTrue(driver.findElement(YES_BUTTON).isEnabled());
        driver.findElement(YES_BUTTON).click();
    }

    public void fillOutOrderForm_choosingTwoColors() {
        driver.findElement(INPUT_NAME).sendKeys("Петя");
        driver.findElement(INPUT_LAST_NAME).sendKeys("Батарейкин");
        driver.findElement(INPUT_ADDRESS).sendKeys("ул. Попова");
        selectMetro("Черкизовская");
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys("+79215552121");
        driver.findElement(NEXT_BUTTON).click();
        waitForChangedHeader();
        driver.findElement(RENT_DATE).sendKeys("01.04.2023");
        driver.findElement(DROPDOWN_ARROW).click();
        driver.findElement(FOUR_DAYS_RENT).click();
        driver.findElement(CHECKBOX_BLACK).click();
        driver.findElement(CHECKBOX_GREY).click();
        driver.findElement(COMMENT).sendKeys(" ");
        Assert.assertTrue(driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).isEnabled());
        driver.findElement(ORDER_BUTTON_UNDER_FOR_WHOM_FORM).click();
        Assert.assertTrue(driver.findElement(YES_BUTTON).isEnabled());
        driver.findElement(YES_BUTTON).click();
    }
}

