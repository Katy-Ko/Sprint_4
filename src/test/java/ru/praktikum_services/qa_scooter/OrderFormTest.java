package ru.praktikum_services.qa_scooter;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderFormTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkCompoundName_doesNotShowError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutPersonalData_withCompoundName();
    }
    //В задании указано "Проверить ошибки для всех полей формы заказа.". Что значит "Проверить ошибки?" :)
    //Первые два теста сделала так, что они падают, но в идеале не должны

    @Test
    public void checkCompoundLastName_showsError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutPersonalData_withCompoundLastName();
    }

    @Test
    public void checkNonexistentAddress_doesNotShowError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutPersonalData_withNonexistentAddress();
    }
    //Тут ошибка в адресе, но тест пройдёт

    @Test
    public void checkNonexistentPhone_doesNotShowError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutPersonalData_withNonexistentPhone();
    }

    @Test
    public void checkPastDate_doesNotShowError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutOrderForm_withPastDate();
    }

    @Test
    public void checkChoosingTwoColors_doesNotShowError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutOrderForm_choosingTwoColors();
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}

