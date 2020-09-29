package ru.appline.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.ManagerPages;

import java.util.List;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {
    protected ManagerPages app = ManagerPages.getManagerPages();

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 50, 200);

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void fillInputField(WebElement field, String value) {
        if (!(field.isDisplayed())) {
            scrollToElementJs(field);
        }
        int i = field.getAttribute("value").length();
        do {
            field.sendKeys(Keys.BACK_SPACE);
            i--;
        } while (i > 0);
        field.sendKeys(value);
    }

    public void clicker(WebElement element) {
        if (!element.isDisplayed()) {
            scrollToElementJs(element);
        }
        element.click();
    }


    public void sleeper(long a) {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectMenu(List<WebElement> b, String name) {
        for (WebElement a : b) {
            if (a.getText().contains(name)) {
                clicker(a);
            }
        }
    }

    public void checkTextT(WebElement a, String name, String error) {
        wait.until(ExpectedConditions.visibilityOf(a));
        if (!(a.getText().contains(name))) {
            throw new AssertionError(error);
        }
    }

    public void checkText(WebElement a, String name, String error) {
        try {
            wait.until(texttToBePresentInElement(a, name));
        } catch (TimeoutException ex) {
            throw new AssertionError("Поле не выдает ожидаемое значение");
        }
    }

    public void checkAndSetSwitch(WebElement a, String znach) {

        int i = 0;
        do {
            if (!(a.getAttribute("aria-checked").equals(znach))) {
                clicker(a);
            }
            sleeper(100);
            i++;
        } while (!(a.getAttribute("aria-checked").equals(znach)) || i >= 10);
        if (!(a.getAttribute("aria-checked").equals(znach))) {
            throw new AssertionError("Свич не переключился");
        }
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

}


