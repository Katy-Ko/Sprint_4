package ru.praktikum_services.qa_scooter;
import model.MainPage;
import org.junit.After;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FAQTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkQuestionShowsRightAnswer() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadServiceLogo();
        objMainPage.clickCookieButton();
        objMainPage.scrollToQuestionsList();
        objMainPage.clickFirstQuestion();
        objMainPage.verifyFirstAnswerText();
        objMainPage.clickLastQuestion();
        objMainPage.verifyLastAnswerText();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

