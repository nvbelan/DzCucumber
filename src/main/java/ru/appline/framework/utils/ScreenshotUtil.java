package ru.appline.framework.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil extends AllureCucumber5Jvm {


    @Attachment(value = "filen",type = "text/plain")
    public static byte[] getBytesAnnotationWithArgs() throws IOException {

        return Files.readAllBytes(Paths.get("src/main/resources/text.txt"));
    }
}


