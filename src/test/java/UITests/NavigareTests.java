package UITests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigareTests {

    WebDriver driver;

    @BeforeMethod
    public void instantareBrowser(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void inchideBrowser(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test(priority = 2)
    public void exercitiuNavigare1() {
        driver.get("https://demoqa.com/");

        String titlulPaginii = driver.getTitle();
        System.out.println(titlulPaginii);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.navigate().to("https://demoqa.com/elements");

        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.navigate().back();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().forward();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void originalTabTest() {
        driver.get("https://demoqa.com/");

        String originalTab = driver.getWindowHandle();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://demoqa.com/forms");

        driver.switchTo().window(originalTab);
    }

    @Test
    public void exercitiu2() {
        driver.get("https://demoqa.com/");

        driver.get("https://demoqa.com/elements");
        String elementsTitle = driver.getTitle();

        driver.get("https://demoqa.com/forms");

        driver.navigate().back();
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("elements"), "Nu suntem pe pagina Elements");

        driver.navigate().forward();
        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("forms"), "Nu suntem pe pagina Forms");

        driver.navigate().refresh();
    }

    @Test(priority = 0)
    public void threeTabs(){
        driver.get("https://demoqa.com/");
        String mainTab = driver.getWindowHandle();

        System.out.println(driver.getTitle());

        // TAB 1
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://demoqa.com/elements");
        System.out.println(driver.getTitle());
        driver.close();

        // revenim pe tab-ul existent
        driver.switchTo().window(mainTab);

        // TAB 2
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://demoqa.com/forms");
        System.out.println(driver.getTitle());

        // TAB 3
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://demoqa.com/alerts");
        System.out.println(driver.getTitle());
        driver.close();

        driver.quit();

    }
}