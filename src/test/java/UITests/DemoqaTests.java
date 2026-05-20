package UITests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.DriverFactory;

import java.time.Duration;

public class DemoqaTests {

    private WebDriver driver;

    @AfterMethod
    public void inchideBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        DriverFactory.quitDriver();
        driver = null;
    }

    @Test
    public void textBoxTest() {

        Reporter.log("Acum incepe testul...");

        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");

        String fullNameValue = "Adelina";

        Actions actions = new Actions(driver);

        Reporter.log("Aici se identifica elementele cu care interactionez.. ");
        WebElement fullName = driver.findElement(By.id("userName"));
        WebElement email = driver.findElement(By.id("userEmail"));
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));

        Reporter.log("Se introduc datele..");
        fullName.sendKeys(fullNameValue);
        email.sendKeys("test@itschool.com");
        currentAddress.sendKeys("Timisoara");
        permanentAddress.sendKeys("Timis");

        clickSubmitButton();


        WebElement output = driver.findElement(By.id("output"));



//        Assert.assertTrue(output.isDisplayed());


        Reporter.log("Verificarea..");
        String outputName = driver.findElement(By.id("name")).getText();

        System.out.println(outputName);

        Assert.assertTrue(outputName.contains(fullNameValue), "Output name nu este corect");

    }

    @Test
    public void textBoxTestNegativ() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");

        Actions actions = new Actions(driver);

        WebElement fullName = driver.findElement(By.id("userName"));
        WebElement email = driver.findElement(By.id("userEmail"));
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));

        fullName.sendKeys("Adelina");
        email.sendKeys("test");
        currentAddress.sendKeys("Timisoara");
        permanentAddress.sendKeys("Timis");

        clickSubmitButton();

        String classAttribute = email.getAttribute("class");

        System.out.println(classAttribute);
        //comentariu la linia 78

        Assert.assertTrue(classAttribute.contains("field-error"));

        //acesta este un comentariu

    }

    private void clickSubmitButton() {
        By submitButton = By.id("submit");
        WebElement submit = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(submitButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                submit
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(submitButton))
                .click();
    }
}
