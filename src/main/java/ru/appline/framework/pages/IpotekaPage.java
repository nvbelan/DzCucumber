package ru.appline.framework.pages;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IpotekaPage extends BasePage {
    @FindBy(xpath = "//div[@class = 'kit-col_xs_12 kit-col_md_0 kit-col_lg_6 kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40']/h1")
    WebElement title;
    @FindBy(xpath = "//b[text()='Подать заявку']")
    WebElement button;
    @Тогда("Проверяем переход на страницу")
    public IpotekaPage checkTitle() {
        checkTextT(title, "Ипотека от", "Переход на страницу не произошел");
        return this;
    }
    @Когда("Нажимаем кнопку Подать заявку")
    public ZayavkaPage pushButtonZayavka(){
        clicker(button);
        return app.getZayavkaPage();
    }

}
