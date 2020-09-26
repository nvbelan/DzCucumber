package ru.appline.framework.managers;

import ru.appline.framework.pages.*;


public class ManagerPages {


    private static ManagerPages managerPages;


    StartPage startPage;


    IpotekaPage ipotekaPage;


    ZayavkaPage zayavkaPage;




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


    public IpotekaPage getIpotekaPage() {
        if (ipotekaPage == null) {
            ipotekaPage = new IpotekaPage();
        }
        return ipotekaPage;
    }


    public ZayavkaPage getZayavkaPage() {
        if (zayavkaPage == null) {
            zayavkaPage = new ZayavkaPage();
        }
        return zayavkaPage;
    }
}




