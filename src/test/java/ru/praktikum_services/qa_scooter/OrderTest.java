package ru.praktikum_services.qa_scooter;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static model.MainPage.PAGE_URL;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    public OrderTest
            (String name, String lastName, String address, String metro, String phoneNumber,
                    String date, String period, String color, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getOrderDetails() {
        return new Object[][] {
                {"Вася", "Пупкин", "ул. Ватутина, 25", "Черкизовская", "+79111111111", "23.03.2023", "сутки", "чёрный жемчуг", "В 17:00 возле подъезда №1"},
                {"Ян", "По", "Победы, 7", "Первомайская", "88004005050", "25.04.2024", "четверо суток", "серая безысходность", "-"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkSuccessfulOrder_topButton() {

        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.setOrderDetails(name, lastName, address, metro, phoneNumber, date, period, color, comment);
        Assert.assertTrue(objOrderPage.isOrderCreatedStatusDisplayed());
    }

    @Test
    public void checkSuccessfulOrder_lowerButton() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.scrollToOrderButtonBottom();
        objOrderPage.clickOrderButtonBottom();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.setOrderDetails(name, lastName, address, metro, phoneNumber, date, period, color, comment);
        Assert.assertTrue(objOrderPage.isOrderCreatedStatusDisplayed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}

