package ru.appline.framework.pages;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class ZayavkaPage extends BasePage {
    @FindBy(xpath = "//h1[@class='shell_commonHeadingHeader']")
    WebElement title;
    @FindBy(xpath = "//div[@data-label='Цель кредита']//input[@class='dc-input__input-4-7-0']")
    WebElement loanPurpose;
    @FindBy(xpath = "//span[text()='Есть зарплатная карта Сбербанка']/..//input")
    WebElement sWitchCard;
    @FindBy(xpath = "//span[text()='Есть возможность подтвердить доход справкой']/..//input")
    WebElement certificateSwitch;
    @FindBy(xpath = "//span[text()='Использовать материнский капитал']/..//input")
    WebElement motherMoneySwitch;
    @FindBy(xpath = "//span[contains(text(),'Скидка')]/../..//input[@type = 'checkbox']")
    WebElement domClickSaleSwitch;
    @FindBy(xpath = "//span[contains(text(),'Страхование жизни')]/../..//input[@type = 'checkbox']")
    WebElement lifeInsuranceSwitch;
    @FindBy(xpath = "//span[contains(text(),'Молодая семья')]/../..//input[@type = 'checkbox']")
    WebElement youngFamilySwitch;
    @FindBy(xpath = "//span[contains(text(),'Электронная регистрация сделки')]/../..//input[@type = 'checkbox']")
    WebElement webDealSwitch;
    @FindBy(xpath = "//div[@data-label='Стоимость недвижимости']/input")
    WebElement costHouse;
    @FindBy(xpath = "//div[@data-label='Первоначальный взнос']/input")
    WebElement yourMoney;
    @FindBy(xpath = "//div[@data-label='Срок кредита']/input")
    WebElement years;
    @FindBy(xpath = "//span[text()='Процентная ставка']/..//span[contains(text(),'%')]")
    WebElement procent;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']/span")
    WebElement monthPayment;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']/span")
    WebElement creditSumm;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-required-income']/span")
    WebElement needIncome;

    public WebElement giveWebElementSwitch(String a) {
        WebElement element = null;
        switch (a) {
            case "Зарплатная карта":
                element = sWitchCard;
                break;
            case "ЗП сертификат":
                element = certificateSwitch;
                break;
            case "Материнский капитал":
                element = motherMoneySwitch;
                break;
            case "Скидка домклик":
                element = domClickSaleSwitch;
                break;
            case "Страхование жизни":
                element = lifeInsuranceSwitch;
                break;
            case "Молодая семья":
                element = youngFamilySwitch;
                break;
            case "Онлайн сделка":
                element = webDealSwitch;
                break;


            default:
                Assert.fail("Поле с наименованием '" + a + "' отсутствует на странице ");

        }
        return element;
    }

    public WebElement giveWebElementValue(String a) {
        WebElement element = null;
        switch (a) {
            case "Стоимость недвижимости":
                element = costHouse;
                break;
            case "Первоначальный взнос":
                element = yourMoney;
                break;
            case "Количество лет":
                element = years;
                break;

            default:
                Assert.fail("Поле с наименованием '" + a + "' отсутствует на странице ");

        }
        return element;
    }

    public WebElement giveWebElementCheck(String a) {
        WebElement element = null;
        switch (a) {
            case "ЕП":
                element = monthPayment;
                break;
            case "Сумма кредита":
                element = creditSumm;
                break;
            case "Уровень дохода":
                element = needIncome;
                break;
            case "Процентная ставка":
                element = procent;
                break;

            default:
                Assert.fail("Поле с наименованием '" + a + "' отсутствует на странице ");

        }
        return element;
    }


    public ZayavkaPage checkTitle() {
        checkTextT(title, "Выберите программу и рассчитайте условия", "Переход на страницу не произведен");
        return this;
    }


    public ZayavkaPage checkAndSetLoanPurpose() {

        wait.until(ExpectedConditions.visibilityOf(loanPurpose));
        if (!(loanPurpose.getAttribute("value").contains("Готовое жилье"))) {
            throw new AssertionError("Неверно предустановлена цель кредита");
        }
        return this;
    }


    public ZayavkaPage setSwitch(String webE, String value) {
        checkAndSetSwitch(giveWebElementSwitch(webE), value);
        return this;
    }


    public ZayavkaPage fillValue(String webE, String value) {

        fillInputField(giveWebElementValue(webE), value);

        return this;
    }

    public ZayavkaPage checkFills(String webE, String value, String mess) {
        checkText(giveWebElementCheck(webE), value, mess);
        return this;

    }


}
