package CucumberExercise;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Top100CoinsStepDefinitions {
    public static WebDriver driver;
    public static WebDriverWait wdWait;
    protected static JavascriptExecutor js;

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdWait = new WebDriverWait(driver, 20);
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://coinmarketcap.com/");
    }

    @When("I choose Coins from Cryptocurrencies section")
    public void i_choose_coins_from_cryptocurrencies_section() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("kHLEqq")));
        WebElement cryptocurrenciesSection = driver.findElement(By.className("kHLEqq"));
        List<WebElement> coockies = driver.findElements(By.className("cmc-cookie-policy-banner__close"));
        if (coockies.size() > 0) {
            js.executeScript("arguments[0].click();", coockies.get(0));
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(cryptocurrenciesSection).perform();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div[1]/nav/ul/li[1]/div/div/div[2]/div/div[2]/div/div[1]/div/div[3]")));
        WebElement coins = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div[1]/nav/ul/li[1]/div/div/div[2]/div/div[2]/div/div[1]/div/div[3]"));
        coins.click();

    }

    @Then("it should be displayed top {int} cryptocurrencies for coins")
    public void it_should_be_displayed_top_cryptocurrencies_for_coins(Integer int1) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div[3]/table")));
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[3]/table/tbody/tr"));
        rows.remove(0);
        int count = rows.size();
        Assert.assertEquals(count, 100);
        driver.close();
        driver.quit();

    }
}
