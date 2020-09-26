package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.ManagerPages;

public class Steps {
    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Выбираем меню Ипотека$")
    public void step1() {
        app.getStartPage().selectMenuIpoteka();
    }

    @Когда("Выбираем подменю Ипотека на готовое жильё")
    public void step2() {
        app.getStartPage().selectSecMenu();
    }

    @Тогда("Проверяем переход на страницу")
    public void step3() {
        app.getIpotekaPage().checkTitle();
    }

    @Когда("Нажимаем кнопку Подать заявку")
    public void step4() {
        app.getIpotekaPage().pushButtonZayavka();
    }

    @Тогда("Проверяем заголовок Выберите программу и рассчитайте условия")
    public void step5() {
        app.getZayavkaPage().checkTitle();
    }

    @Тогда("Проверяем цель кредита")
    public void step6() {
        app.getZayavkaPage().checkAndSetLoanPurpose();
    }

    @Когда("Проверяем и устанавливаем переключатели")
    public void step7(DataTable dataTable) {
        dataTable.cells().forEach(raw -> {
                    app.getZayavkaPage().setSwitch(raw.get(0), raw.get(1));
                }
        );
    }

    @Когда("Заполняем поля")
    public void step8(DataTable dataTable) {
        dataTable.cells().forEach(raw -> {
                    app.getZayavkaPage().fillValue(raw.get(0), raw.get(1));
                }
        );
    }

    @Тогда("Проверяем поля")
    public void step9(DataTable dataTable) {
        dataTable.cells().forEach(raw -> {
                    app.getZayavkaPage().checkFills(raw.get(0), raw.get(1), raw.get(2));
                }
        );
    }
}

