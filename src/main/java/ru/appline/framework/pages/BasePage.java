package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.appline.framework.managers.DriverManager;

import java.util.ArrayList;
import java.util.List;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 20, 1000);

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void fillInputField(WebElement field, String value) {
        if (!(field.isDisplayed())) {
            scrollToElementJs(field);
        }
        wait.until(ExpectedConditions.visibilityOf(field));
        field.sendKeys(Keys.CONTROL, "a");
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(value);
        Assert.assertEquals("Залолнение поля произведено с ошибкой", field.getAttribute("value"), value);
    }

    public void clicker(WebElement element) {
        if (!element.isDisplayed()) {
            scrollToElementJs(element);
        }
        waitLoadPage();
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    public void sleeper(long a) {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement selectInList(List<WebElement> b, String name) {
        WebElement temp = null;
        for (WebElement a : b) {
            if (a.getText().contains(name)) {
                temp = a;
                break;
            }
        }
        if (temp == null) {
            throw new AssertionError("Элемент с данным текстом отсутствует в List");
        }
        return temp;
    }

    public ExpectedCondition<Boolean> texttToBePresentInElement(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText().replaceAll("[^0-9]", "");
                    return elementText.contains(text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }

    protected void waitLoadPage() {
        wait.until(
                driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                        .equals("complete"));
    }

    protected int createListTemp(WebElement element, String a) {
        List<WebElement> s = new ArrayList<>();
        s = element.findElements(By.xpath(a));
        return s.size();
    }

    public void superWait(WebElement a) {
        waitLoadPage();
        wait.until(ExpectedConditions.visibilityOf(a));
        wait.until(ExpectedConditions.elementToBeClickable(a));
    }
    public void logicalWait(int iterat, int chastotaOprosa,List<WebElement> list,int temp , String message) {
        int timer = 0;
        do {
            sleeper(chastotaOprosa);
            timer++;
        } while (temp >= list.size()&& timer<iterat);
        if(timer>=iterat){
            throw new AssertionError(message);
        }
    }

}


