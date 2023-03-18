package ru.yandex.praktikum.sprint4.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.sprint4.HomePage;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HomePageTest {
    private final String question;
    private final String answer;

    private WebDriver driver;

    public HomePageTest(String question, String answer){
        this.answer = answer;
        this.question = question;
    }

    @Parameterized.Parameters(name = "Тестовые данные: Вопрос = {0}, Ответ = {1}")
    public static Object[][] data() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."}
        };
    }

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Проверка соответствия ответа на любой вопрос
    @Test
    public void checkAnswerTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnAcceptCookies();
        String actual = homePage.scrollAccordionHeadingQuestion(question);
        assertEquals(answer, actual);
    }

    // После принятий кук кнопка не отображается
    @Test
    public void checkClickCookieButtonTest() {
        HomePage homePage = new HomePage(driver);
        if (homePage.isDisplayedAcceptCookiesButton()) {
            homePage.clickOnAcceptCookies();
        }
        assertFalse(homePage.isDisplayedAcceptCookiesButton());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
