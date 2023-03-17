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

public class ReturnToMainPageTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

