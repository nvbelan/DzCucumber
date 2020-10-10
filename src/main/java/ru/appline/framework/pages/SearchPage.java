package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.utils.Product;
import ru.appline.framework.utils.ScreenshotUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ru.appline.framework.utils.Products.*;


public class SearchPage extends BasePage {
    @FindBy(xpath = "//div[contains(text(),'Цена')]/../div[@step]/div//p")
    List<WebElement> filterCost;
    @FindBy(xpath = "//div[contains(@title,'Популярные')]")
    WebElement checableLoadPage;
    @FindBy(xpath = "//div[contains(text(),'Беспроводные интерфейсы')]/following-sibling::div/div/span//span")
    List<WebElement> noProvodTech;
    @FindBy(xpath = "//div[contains(@value,'Высокий рейтинг')]//input/..")
    WebElement highRaiting;
    @FindBy(xpath = "//div[contains(@style, 'span')]")
    List<WebElement> products;
    @FindBy(xpath = "//a[@href='/cart']")
    WebElement basket;
    @FindBy(xpath = "//a[@class='cw'] [contains(text(),'Электроника')]")
    WebElement katElectr;
    @FindBy(xpath = "//a[@class='cw'] [contains(text(),'Телефоны и смарт-часы')]")
    WebElement telSmart;
    @FindBy(xpath = "//a[@class='cw'] [contains(text(),'Смартфоны')]")
    WebElement telephone;
    @FindBy(xpath = "//div[contains(text(),'Бренды')]/following-sibling::div/span")
    WebElement allBrands;
    @FindBy(xpath = "//div[contains(text(),'Бренды')]/following-sibling::div/div/input")
    WebElement searchBrands;
    @FindBy(xpath = "//div/a/label/div/span")
    WebElement firstSearch;
    @FindBy(xpath = "//div[@data-widget='searchResultsFiltersActive']/div/div//span")
    List<WebElement> filterList;
    @FindBy(xpath = "//a[@data-widget='cart']/span")
    WebElement numberBasket;

    public void setCost(String namefill, String value) {
        int temp = filterList.size();
        superWait(checableLoadPage);
        WebElement fill = selectInList(filterCost, namefill);
        fill = fill.findElement(By.xpath("./../input"));
        fillInputField(fill, value);
        fill.sendKeys(Keys.ENTER);
        logicalWait(10,500,filterList,temp,"Фильтр не отработал");

    }

    public void setNoProvod(String fill, boolean y) {
        int temp = filterList.size();
        superWait(checableLoadPage);
        WebElement tick = selectInList(noProvodTech, fill);
        if (tick.isSelected() != y) {
            tick.click();
        }
        logicalWait(10,500,filterList,temp,"Фильтр не отработал");
    }

    public void setRaiting(boolean y) {
        int temp = filterList.size();
        superWait(checableLoadPage);
        if (highRaiting.isSelected() != y) {
            clicker(highRaiting);
        }
        logicalWait(10,500,filterList,temp,"Фильтр не отработал");
    }

    public void addToCart(String countS) {
        int j = 0;
        int count;
        boolean marker = false;
        if (countS.equals("все")) {
            count = products.size() / 2;
        } else {
            count = Integer.parseInt(countS);
            marker = true;
        }
        if (products.size() >= (count * 2)) {
            for (int i = 1; i < count * 2; i = i + 2) {
                if (!(products.get(i).findElement(By.xpath("./div/div/div[3]/div[3]//button/div/div"))
                        .getText().contains("В корзину"))
                        || createListTemp(products.get(i), "./div/div/div/a/div/section/span") >= 2) {
                    if (marker) {
                        count = count + 1;
                    }
                } else {
                    scrollToElementJs(products.get(i));
                    products.get(i).findElement(By.xpath(".//div[text()='В корзину']")).click();
                    j++;
                    int timer=0;
                    do {
                        timer++;
                        sleeper(200);
                    } while (!numberBasket.getText().equals(String.valueOf(j))&&timer<30);
                    if(timer>=30){
                        throw new AssertionError("Элемент не добавлен в корзину");
                    }
                    getProductList().add(new Product((products.get(i).
                            findElement(By.xpath("//div[contains(@style,'50')]//a[contains(@class, 'tile-hover-target')]"))
                            .getText()), Integer.parseInt(products.get(i).
                            findElement(By.xpath(".//span[contains(text(),'₽')]")).getText().replaceAll("[^0-9]", ""))));
                }

            }
        }
    }

    public void genTXT() {
        try {
            FileWriter writer = new FileWriter("src/main/resources/text.txt", true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            int temp = 0;
            int max = getProductList().get(0).getPrice();
            for (int i = 0; i < getProductList().size(); i++) {
                bufferWriter.write("Наименование " + getProductList().get(i).getName()
                        + " Цена " + getProductList().get(i).getPrice() + "\n");
                if (getProductList().get(i).getPrice() > max) {
                    temp = i;
                    max = getProductList().get(i).getPrice();
                }
            }
            bufferWriter.write("Товар с наибольшей ценой " + getProductList().get(temp).getName() + " "
                    + getProductList().get(temp).getPrice());
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            ScreenshotUtil.getBytesAnnotationWithArgs();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickToBusket() {
        clicker(basket);
    }

    public void katSel() {
        superWait(checableLoadPage);
        clicker(katElectr);
        superWait(checableLoadPage);
        clicker(telSmart);
        superWait(checableLoadPage);
        clicker(telephone);
    }

    public void setBrands(String a, String b) {
        superWait(checableLoadPage);
        int temp = filterList.size();
        clicker(allBrands);
        fillInputField(searchBrands, a);
        clicker(firstSearch);
        logicalWait(10,500,filterList,temp,"Фильтр не отработал");
        temp = filterList.size();
        fillInputField(searchBrands, b);
        clicker(firstSearch);
        logicalWait(10,500,filterList,temp,"Фильтр не отработал");


    }
}


