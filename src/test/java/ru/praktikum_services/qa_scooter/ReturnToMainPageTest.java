package ru.praktikum_services.qa_scooter;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static model.MainPage.PAGE_URL;

public class ReturnToMainPageTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkClickOnLogoReturnsToMainPage() {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objMainPage.waitForLoadServiceLogo();
        objOrderPage.clickOrderButtonTop();
        objOrderPage.waitForLoadOrderHeader();
        objMainPage.clickServiceLogo();
        Assert.assertTrue(objMainPage.IsHomeHeaderDisplayed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}

