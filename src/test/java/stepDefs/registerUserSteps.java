package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class registerUserSteps {
    private WebDriver driver;
    private String randomEmail;

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I initiate {string}")
    public void initiate(String browser) {
        switch (browser) {
            case "Firefox":
                driver = new FirefoxDriver();
                break;

            case "Chrome":
                driver = new ChromeDriver();
                break;

            case "Edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();

       this.randomEmail = "";
    }

    @Given("I navigate to Create An Account")
    public void navigateToCreateAnAccount() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I type {string} in the {string} field")
    public void typeInField(String input, String field) {
        sendKeys(By.cssSelector("input[name='" + field + "']"), input);
    }

    @When("I type random email in the {string} field")
    public void randomEmail(String field) {
        if (this.randomEmail.isEmpty()){
            int randomNumber = new Random().nextInt(100);
            this.randomEmail = "joey.doe_"+randomNumber+"@tester.com";
        }

        sendKeys(By.cssSelector("input[name='" + field + "']"), this.randomEmail);
    }

    @When("I check {string} boxes")
    public void checkTheBox(String toCheck) {
        if (!toCheck.isEmpty()) {
            String[] checkboxes = toCheck.split(", ");

            for (String checkbox : checkboxes) {
                WebElement element = driver.findElement(By.cssSelector("input[name='" + checkbox + "']"));
                String id = element.getDomAttribute("id");
                click(By.cssSelector("label[for='" + id + "']"));
            }
        }
    }

    @When("I click {string}")
    public void click(String btn) {
        click(By.cssSelector("input[value='" + btn + "']"));
    }

    @Then("I verify registration success")
    public void successfulReg() {
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";

        String actual = driver.findElement(By.cssSelector("h2")).getText();

        assertEquals(expected, actual);
    }

    @Then("I expect this {string}")
    public void unsuccessfulReg(String expected) {
        String actual = "";

        List<WebElement> elements = driver.findElements(By.cssSelector("span[class*='warning']"));
        for (WebElement element : elements) {
            if (!element.getText().isEmpty()) {
                actual += element.getText() + "\n";
            }
        }

        assertEquals(expected, actual.strip());
    }

    private void sendKeys(By by, String str) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(str + Keys.ENTER);
    }

    private void click(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }
}
