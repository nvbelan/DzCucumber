package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

import static ru.appline.framework.utils.ScreenshotUtil.getBytesAnnotationWithArgs;


public class StartPage extends BasePage {
    @FindBy(xpath = "//input[@name='search']")
    WebElement searchList;
    @Step("Заполняем поле поиска")
    public StartPage searchfill(String search) {
        waitLoadPage();
        wait.until(ExpectedConditions.visibilityOf(searchList));
        fillInputField(searchList,search);
        searchList.sendKeys(Keys.ENTER);
        return this;
    }

}
