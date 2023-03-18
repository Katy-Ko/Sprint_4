package ru.praktikum_services.qa_scooter;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static model.MainPage.PAGE_URL;

@RunWith(Parameterized.class)
public class OrderFormTest {

    private WebDriver driver;

    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String errorText;

    public OrderFormTest
            (String name, String lastName, String address, String metro, String phoneNumber, String errorText) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.errorText = errorText;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderDetails() {
        return new Object[][] {
                {" ", "Пупкин", "ул. Ватутина, 25", "Черкизовская", "+79111111111", "Введите корректное имя"},
                {"Ян", " ", "Победы, 7", "Первомайская", "88004005050", "Введите корректную фамилию"},
                {"Александра", "Суворова", " ", "Красные Ворота", "+79215556363", "Введите корректный адрес"},
                //этот тест упадёт, потому что поле с адресом фактически необязательно
                {"Анна", "Николаева", "ул. Пушкина, 1", " ", "+79226589654", "Выберите станцию"},
                // этот тест проходит только потому, что поле "Метро" у меня по сути всегда пустое :(
                // не поняла, как оставить его пустым, если использовать метод selectMetro(), который не предполагает
                // пустое поле
                {"Алекс", "Мело", "пр. Победы, 25", "Лубянка", "  ", "Введите корректный номер"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkEmptyField_ShowsError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objOrderPage.fillOutPersonalData(name, lastName, address, metro, phoneNumber);
        objOrderPage.isErrorTextDisplayed(errorText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

