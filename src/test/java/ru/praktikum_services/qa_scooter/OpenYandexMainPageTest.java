package ru.praktikum_services.qa_scooter;
import model.MainPage;
import org.junit.After;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static model.MainPage.PAGE_URL;
import static org.junit.Assert.assertEquals;

public class OpenYandexMainPageTest {

    private WebDriver driver;

    private final String YANDEX_URL = "https://dzen.ru/?yredirect=true";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkClickYandexLogo_OpensYandexMainPage() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickYandexLogo();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(YANDEX_URL, currentUrl);
        /* Не уверена в правильности выбранного решения, т.к. наиболее подходящим казалось использовать
        urlMatches, но не разобралась с ним. По идее в методе clickYandexLogo и так уже проверяется факт появления
        title "Дзен". Не знаю, стоило ли сравнивать ссылки.
         */
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}



