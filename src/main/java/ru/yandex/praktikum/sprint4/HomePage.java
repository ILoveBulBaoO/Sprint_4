package ru.yandex.praktikum.sprint4;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    // Кнопка "Заказать" сверху справа
    private By orderUpRight = By.xpath(".//button[@class='Button_Button__ra12g']");

    // Кнопка "Заказать" снизу
    private By orderDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    // Кнопка "Статус заказа"
    private By orderStatus = By.xpath(".//button[@class='Header_Link__1TAG7']");

    // Поле "Введите номер заказ"
    private By inputOrderNumberForCheckStatus = By.className("Input_InputContainer__3NykH");

    // Кнопка "Go" для проверки заказа
    private By goButtonForCheckOrderStatus = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    // Кнопка для принятия кук "да все привыкли"
    private By acceptCookies = By.id("rcc-confirm-button");

    // Раздел "Вопросы о важном"
    private By importantQuestion = By.xpath(".//div[@class='accordion__heading']");

    // Ответы на "Вопросы о важном"
    private By answerImportantQuestion = By.xpath(".//div[@class='accordion__panel' and not(@hidden)]/p");

    // Лого Яндекс
    private By logoYandex = By.className("Header_LogoYandex__3TSOI");

    // Лого Самокат
    private By logoScooter = By.className("Header_LogoScooter__3lsAR");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Нажать на Заказать справа вверху
    public void clickOnOrderUpRight() {
        driver.findElement(orderUpRight).click();
    }

    // Скрол и нажать на Заказать снизу
    public void scrollAndClickOnButtonOrderDown() {
        WebElement element = driver.findElement(orderDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderDown).click();
    }

    // Нажать на лого Яндекс
    public void clickOnLogoYandex() {
        driver.findElement(logoYandex).click();
    }

    // Нажать на лого Самоката
    public void clickOnLogoScooter() {
        driver.findElement(logoScooter).click();
    }

    // Нажать на кнопку Кук
    public void clickOnAcceptCookies() {
        driver.findElement(acceptCookies).click();
    }

    // Проверка отображение кнопки кук
    public boolean isDisplayedAcceptCookiesButton() {
        try {
            return driver.findElement(acceptCookies).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Нажать на кнопку проверки заказа
    public void clickOnOrderStatus() {
        driver.findElement(orderStatus).click();
    }

    // Очистить поле и вставить номер заказа
    public void insertOrderNumberForCheck(String orderNumber) {
        driver.findElement(inputOrderNumberForCheckStatus).clear();
        driver.findElement(inputOrderNumberForCheckStatus).sendKeys(orderNumber);
    }

    // Нажатие на кнопку Go
    public void clickOnGoButton() {
        driver.findElement(goButtonForCheckOrderStatus).click();
    }

    // Проверка статуса заказ
    public void checkOrderStatus(String orderNumber) {
        clickOnOrderStatus();
        insertOrderNumberForCheck(orderNumber);
        clickOnGoButton();
    }

    // Доскроллить до нужного блока c вопросом и нажать
    public String scrollAccordionHeadingQuestion(String question) {
        WebElement element = driver.findElement(By.xpath(".//div[text()='" + question + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answerImportantQuestion));
        return driver.findElement(answerImportantQuestion).getText();
    }

}
