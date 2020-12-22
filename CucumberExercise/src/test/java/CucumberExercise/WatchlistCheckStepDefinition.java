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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WatchlistCheckStepDefinition {
    public static WebDriver driver;
    public static WebDriverWait wdWait;
    public static JavascriptExecutor js;

    List<String> namesOfCryptocurrenciesFilteredList = new ArrayList<>();
    List<String> namesOfCryptocurrenciesWatchlist = new ArrayList<>();


    @Given("user is on the homepage")
    public void user_is_on_the_homepage() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdWait = new WebDriverWait(driver, 20);
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://coinmarketcap.com/");
    }

    @When("I click on Filters button")
    public void i_click_on_filters_button() {
        List<WebElement> coockies = driver.findElements(By.className("cmc-cookie-policy-banner__close"));
        if (coockies.size() > 0) {
            js.executeScript("arguments[0].click();", coockies.get(0));
        }

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("table-control-filter")));
        WebElement filtersButton = driver.findElement(By.className("table-control-filter"));
        filtersButton.click();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div/div[3]/span/div/button")));
        WebElement priceTab = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div/div[3]/span/div/button"));
        priceTab.click();

    }

    @When("enter Price range from {int}-{int} and click Apply button")
    public void enter_price_range_from_and_click_apply_button(Integer int1, Integer int2) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div/div[3]/div/div/div[1]/div/div/div/div[1]/input[1]")));
        WebElement minPriceRange = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div/div[3]/div/div/div[1]/div/div/div/div[1]/input[1]"));
        minPriceRange.sendKeys("23000");
        WebElement maxPriceRange = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/div/div[3]/div/div/div[1]/div/div/div/div[1]/input[2]"));
        maxPriceRange.sendKeys("99999");
        WebElement applyButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div/div[3]/div/div/div[1]/div/div/div/div[3]/div/button[2]"));
        js.executeScript("arguments[0].scrollIntoView();", applyButton);
        applyButton.click();

    }

    @When("add all filtered cryptocurrencies to Watchlist")
    public void add_all_filtered_cryptocurrencies_to_watchlist() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div[3]/table")));
        //List<WebElement> filteredList = driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div/div[3]/table/tbody/tr"));
        for (int i = 1; i <= 5; i++) {
            String relativeXPathForName = "//*[@id=\"__next\"]/div/div[2]/div/div/div[3]/table/tbody/tr["+i+"]/td[3]/a/div/div/p";
            WebElement name = driver.findElement(By.xpath(relativeXPathForName));
            String nameText = name.getText().trim();
            namesOfCryptocurrenciesFilteredList.add(nameText);
            String relativeXPathForStar = "//*[@id=\"__next\"]/div/div[2]/div/div/div[3]/table/tbody/tr["+i+"]/td[1]/span/span";
            WebElement starIcon = driver.findElement(By.xpath(relativeXPathForStar));
            starIcon.click();
        }
        System.out.println(namesOfCryptocurrenciesFilteredList);
    }

    @When("click on Watchlist tab")
    public void click_on_watchlist_tab() {
        WebElement watchlistTab = driver.findElement(By.className("icon-Star"));
        js.executeScript("arguments[0].scrollIntoView();", watchlistTab);
        watchlistTab.click();
    }

    @Then("all added cryptocurrencies should be displayed")
    public void all_added_cryptocurrencies_should_be_displayed() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[2]/table")));
        WebElement watchlistTable = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[2]/table"));
        js.executeScript("arguments[0].scrollIntoView();", watchlistTable);
        List<WebElement> watchlist = driver.findElements(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[2]/table/tbody/tr"));
        for (int i = 1; i <= watchlist.size(); i++) {
            String relativeXPathForName = "//*[@id=\"__next\"]/div/div[2]/div/div/div[2]/table/tbody/tr[" + i + "]/td[3]/a/div/div/p";
            WebElement name = driver.findElement(By.xpath(relativeXPathForName));
            js.executeScript("arguments[0].scrollIntoView();", name);
            String nameText = name.getText().trim();
            namesOfCryptocurrenciesWatchlist.add(nameText);
        }
        System.out.println(namesOfCryptocurrenciesWatchlist);
        Assert.assertEquals(namesOfCryptocurrenciesFilteredList, namesOfCryptocurrenciesWatchlist);
        driver.close();
        driver.quit();
    }

}
