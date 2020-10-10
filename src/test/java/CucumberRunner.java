import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"ru.appline.framework.utils.ScreenshotUtil",
                "progress",
                "summary"},
        glue = {"steps"},
        tags = {"@ui"},
        features = {"src/test/resources/"}
)
public class CucumberRunner {
}
