package ru.yandex.praktikum.sprint4.tests;

import ru.yandex.praktikum.sprint4.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.sprint4.OrderPage;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderPageTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String adress;
    private final String subwayStation;
    private final String phoneNumber;
    private final String dateOrder;
    private final String periodOrder;

    public OrderPageTest (String name, String surname, String adress, String subwayStation, String phoneNumber, String dateOrder, String periodOrder) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.dateOrder = dateOrder;
        this.periodOrder = periodOrder;
    }

    @Parameterized.Parameters(name = "Тестовые данные: Имя = {0}, Фамилия = {1}, Адрес = {2}, Станция метро = {3}, Телефон = {4}, Когда привезти самокат = {5}, Срок аренды = {6}")
    public static Object[][] data() {
        return new Object[][] {
                {"Миша", "Михайлов", "Мишанина", "Спартак", "+7927722772", "30.03.2023", "сутки"},
                {"Никита", "Никитинин", "Никитина", "ВДНХ", "+7956767867", "01.04.2023", "семеро суток"}
        };
    }

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Заказать через кнопку сверху справа
    @Test
    public void createOrderButtonOrderUpRight() {
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        homePage.clickOnAcceptCookies();
        homePage.clickOnOrderUpRight();

        orderPage.fullOrderFlow(name, surname, adress, subwayStation, phoneNumber, dateOrder, periodOrder);
        assertTrue(orderPage.isSuccessMessage());
    }

    // Заказать через кнопку снизу
    @Test
    public void createOrderButtonOrderDown() {
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        homePage.clickOnAcceptCookies();
        homePage.scrollAndClickOnButtonOrderDown();

        orderPage.fullOrderFlow(name, surname, adress, subwayStation, phoneNumber, dateOrder, periodOrder);
        assertTrue(orderPage.isSuccessMessage());
    }


    @After
    public void tearDown() {
        driver.quit();
    }


}
