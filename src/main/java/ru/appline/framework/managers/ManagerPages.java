package ru.appline.framework.managers;

import ru.appline.framework.pages.*;


public class ManagerPages {


    private static ManagerPages managerPages;


    StartPage startPage;

    SearchPage searchPage;
    BasketPage basketPage;

    private ManagerPages() {
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }


    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}




