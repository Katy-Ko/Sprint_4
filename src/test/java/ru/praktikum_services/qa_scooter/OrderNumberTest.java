package ru.praktikum_services.qa_scooter;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static model.MainPage.PAGE_URL;

@RunWith(Parameterized.class)
public class OrderNumberTest {

    private WebDriver driver;
    private final String orderNumber;

    public OrderNumberTest(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderNumber() {
        return new Object[][] {
                {"000000"},
                {"555-555"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkNonexistentNumber_showsError() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickOrderStatusButton();
        objMainPage.waitForLoadOrderNumberInput();
        objMainPage.enterOrderNumber(orderNumber);
        objMainPage.clickGoButton();
        Assert.assertTrue(objMainPage.isImageNotFoundDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

