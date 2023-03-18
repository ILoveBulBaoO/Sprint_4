package ru.yandex.praktikum.sprint4;

import org.openqa.selenium.*;

public class OrderPage {

    private WebDriver driver;

    // Поле имя
    private By fieldName = By.xpath(".//input[@placeholder='* Имя']");

    // Поле Фамилия
    private By fieldSurName = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле адрес
    private By fieldAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле станция метро
     private By fieldSubway = By.xpath(".//input[@class='select-search__input']");

    // Поле номер телефона
    private By fieldPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private By orderButtonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Поле даты, когда привезти самокат
    private By dateOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Срок аренды
    private By periodOrder = By.xpath(".//div[@class='Dropdown-placeholder']");

    // Кнопка заказа
    private By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    // Кнопка подтверждения "Да"
    private By buttonOrderApprove = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    // Кнопка "Посмотреть статус"
    private By checkOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");

    private By successMessage = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Вставка имени
    public void inputName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    // Встравка фамилии
    public void inputSurName(String surname) {
        driver.findElement(fieldSurName).sendKeys(surname);
    }

    // Встравка адреса
    public void inputAdress(String adress) {
        driver.findElement(fieldAdress).sendKeys(adress);
    }

    // Встравка метро
    public void inputSubway(String subway) {
        driver.findElement(fieldSubway).click();
        driver.findElement(By.xpath(".//div[text() = '" + subway + "']")).click();
    }

    // Вставка номера телефона
    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(fieldPhoneNumber).sendKeys(phoneNumber);
    }

    // Нажатие на кнопку Далее
    public void clickOrderButtonNext() {
        driver.findElement(orderButtonNext).click();
    }

    // Когда доставить
    public void inputDateOrder(String date) {
        driver.findElement(dateOrder).sendKeys(date, Keys.ENTER);
    }

    // На сколько
    public void inputPeriodOrder(String period) {
        driver.findElement(periodOrder).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']")).click();
    }

    // Нажатие на кнопку заказа
    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    // Нажатие на кнопку подтверждения заказа
    public void clickButtonOrderApprove() {
        driver.findElement(buttonOrderApprove).click();
    }

    // Проверка на успешность
    public boolean isSuccessMessage() {
        return driver.findElement(successMessage).isDisplayed();
    }

    // Весь заказ в одном месте
    public void fullOrderFlow(String name, String surname, String adress, String subway, String phoneNumber, String date, String period) {
        inputName(name);
        inputSurName(surname);
        inputAdress(adress);
        inputSubway(subway);
        inputPhoneNumber(phoneNumber);
        clickOrderButtonNext();
        inputDateOrder(date);
        inputPeriodOrder(period);
        clickButtonOrder();
        clickButtonOrderApprove();
    }

}
