package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {
    public static Path takeScreenshot(WebDriver driver, String fileName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Path screenshotsDirectory = Paths.get("screenshots");
            Files.createDirectories(screenshotsDirectory);
            Path screenshotPath = screenshotsDirectory.resolve(fileName + ".png");
            Files.copy(src.toPath(),
                    screenshotPath,
                    StandardCopyOption.REPLACE_EXISTING);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
