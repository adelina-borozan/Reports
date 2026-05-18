package listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utilities.DriverFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverFactory.getExistingDriver();
        if (driver == null) {
            return;
        }

        Path screenshotPath = utils.ScreenshotUtils.takeScreenshot(
                driver,
                result.getName()
        );

        if (screenshotPath != null) {
            try (InputStream screenshot = Files.newInputStream(screenshotPath)) {
                Allure.addAttachment(result.getName(), "image/png", screenshot, ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}