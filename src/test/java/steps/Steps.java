package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import ru.appline.framework.managers.ManagerPages;

import java.io.IOException;
import java.util.List;

import static ru.appline.framework.utils.ScreenshotUtil.getBytesAnnotationWithArgs;

public class Steps {
    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Вводим в поле поиска: (.+)$")
    public void step1(String search) {
        app.getStartPage().searchfill(search);

    }

    @Когда("^Ограничиваем цену товара (от|до) ([0-9]+) рублей$")
    public void step2(String namefill, String value) {
        app.getSearchPage().setCost(namefill, value);
    }

    @Когда("^Выбор беспроводных технологий  (.+) (true|false)$")
    public void step3(String fill, boolean y) {
        app.getSearchPage().setNoProvod(fill, y);
    }
    @Когда("^Установить ползунок Высокий рейтинг (true|false)$")
    public void step4(boolean y) {
        app.getSearchPage().setRaiting(y);
    }

    @Когда("^Добавить четных продуктов ([[0-9]+]||все)$")
    public void step5(String count) {
        app.getSearchPage().addToCart(count);
    }
    @Когда("^Сгенерировать файл$")
    public void step6() {

        app.getSearchPage().genTXT();
        try {
            Allure.getLifecycle().addAttachment("filen","text/plain",null,getBytesAnnotationWithArgs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Когда("^Перейти в корзину$")
    public void step7() {
        app.getSearchPage().clickToBusket();
    }

    @Когда("^Проверить наименование товаров$")
    public void step8() {
        app.getBasketPage().checkProdName();
    }

    @Когда("^Проверить количество товаров в корзине$")
    public void step9() {
        app.getBasketPage().countP();
    }
    @Когда("^Удалить элементы из корзины$")
    public void step10() {
        app.getBasketPage().jmakButton();
    }
    @Когда("^Проверяем заголовок: (.*)$")
    public void step11(String value) {
        app.getBasketPage().empKor(value);
    }
    @Когда("^Выбираем категории$")
    public void step12() {
        app.getSearchPage().katSel();
    }
    @Когда("^Выбираем бренды (.*) (.*)$")
    public void step13(String a, String b){
        app.getSearchPage().setBrands(a,b);
    }




}

