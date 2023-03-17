package ru.praktikum_services.qa_scooter;
import model.MainPage;
import model.YandexPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenYandexMainPageTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkClickYandexLogo_OpensYandexMainPage() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickYandexLogo();

        YandexPage objYandexPage = new YandexPage(driver);
        Assert.assertTrue(objMainPage.isSearchInputDisplayed());
    }
    /*С этим тестом много загвоздок -_- никак не находится нужный элемент, казалось бы, уже в новом окне.
    Будто сменить фокус с одного окна на другое не получилось в итоге.
    Нужно ли вообще было создавать POM для страницы яндекса?
     */

    @After
    public void tearDown() {
        driver.quit();
    }
}



