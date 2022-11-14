package test.Login;

import MarketAlertUmAssignment.PageObjects.Product;
import MarketAlertUmAssignment.PageObjects.SearchResults;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ValidLoginTest {

    WebDriver driver;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {

        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://www.marketalertum.com");

    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() throws Exception{

        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebElement searchField = driver.findElement(By.name("UserId"));
        searchField.sendKeys("65c4902d-14bc-43af-87e0-f5e737ea8333");


        WebElement sumbitButton = driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        String alertImage = driver.findElement(By.tagName("h1")).getText();
        Assertions.assertEquals("Latest alerts for Ryan Scerri", alertImage);
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebElement searchField = driver.findElement(By.name("UserId"));
        searchField.sendKeys("Wrong Password");


        WebElement sumbitButton = driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();

    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {

        String Check = driver.findElement(By.tagName("input")).getAttribute("type");
        Assertions.assertEquals("text", Check);
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws IOException {

        SearchResults src = new SearchResults();
        List<Product> product = new LinkedList<>();

        arg0 = 3;

        src.deleteRequest();

        src.SearchItem("Iphone");

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = src.returnAllProducts();
        product = src.getAllProductInformation(searchResults, arg0,6);

        for (Product products: product) {
            String JsonString = src.JSONString(products);
            src.postRequest2(JsonString);

        }

    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {

        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebElement searchField = driver.findElement(By.name("UserId"));
        searchField.sendKeys("65c4902d-14bc-43af-87e0-f5e737ea8333");


        WebElement sumbitButton = driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();

        List<WebElement> productRestults = new LinkedList<>();
        productRestults = driver.findElements(By.tagName("table"));

    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {

       List<WebElement> icon = new LinkedList<>();

       icon = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr/td/h4/img"));
       Assertions.assertEquals(3,icon.size());

    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {

        List<WebElement> Heading = new LinkedList<>();

        Heading = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr/td/h4"));
        Assertions.assertEquals(3,Heading.size());
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        List<WebElement> Description = new LinkedList<>();

        Description = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[3]/td"));
        Assertions.assertEquals(3,Description.size());
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {

        List<WebElement> Image = new LinkedList<>();

        Image = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[2]/td/img"));
        Assertions.assertEquals(3,Image.size());
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {

        List<WebElement> Price = new LinkedList<>();

        Price = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[4]/td/b"));
        Assertions.assertEquals(3,Price.size());
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        List<WebElement> Link = new LinkedList<>();

        Link = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[5]/td/a"));
        Assertions.assertEquals(3,Link.size());
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws IOException {
        SearchResults src = new SearchResults();
        List<Product> product = new LinkedList<>();

        arg0 = 6;

        src.deleteRequest();

        src.SearchItem("Iphone");

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = src.returnAllProducts();
        product = src.getAllProductInformation(searchResults, arg0, 1);

        for (Product products: product) {
            String JsonString = src.JSONString(products);
            src.postRequest2(JsonString);

        }

    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {

        List<WebElement> Alerts = new LinkedList<>();

        Alerts = driver.findElements(By.xpath("/html/body/div/main/table"));
        Assertions.assertEquals(arg0,Alerts.size());

    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int arg0) throws IOException {

        SearchResults src = new SearchResults();
        List<Product> product = new LinkedList<>();

        int arg1 = 1;

        src.deleteRequest();

        src.SearchItem("Iphone");

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = src.returnAllProducts();
        product = src.getAllProductInformation(searchResults, arg1,arg0);

        for (Product products: product) {
            String JsonString = src.JSONString(products);
            src.postRequest2(JsonString);

        }

    }

    @Then("the icon displayed should be {string}")
    public void theIconDisplayedShouldBeIconFileName(String arg0) {

        String icon;

        icon = driver.findElement(By.xpath("/html/body/div/main/table/tbody/tr/td/h4/img")).getAttribute("src");
        icon = icon.replaceAll("https://www.marketalertum.com/images/", "");
        icon = icon.replaceAll(".jpg", ".png");
        Assertions.assertEquals(arg0, icon);

        driver.close();

    }
}



