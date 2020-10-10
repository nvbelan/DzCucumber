package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import ru.appline.framework.managers.InitManager;
import ru.appline.framework.utils.ScreenshotUtil;

import java.io.IOException;

public class Hooks {

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach() {
//        InitManager.quitFramework();
    }
}
