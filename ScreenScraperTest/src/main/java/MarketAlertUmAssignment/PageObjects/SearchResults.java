package MarketAlertUmAssignment.PageObjects;

import MarketAlertUmAssignment.Interfaces.CheckIfPostRequestWasDoneController;
import MarketAlertUmAssignment.Interfaces.CheckIfWebsiteWasOpenedController;
import MarketAlertUmAssignment.Interfaces.CheckTimePostRequestWasDone;
import MarketAlertUmAssignment.Interfaces.CheckTimeWebsiteWasOpenedController;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class SearchResults {

    WebDriver driver;
     CheckIfWebsiteWasOpenedController CheckWebController;
    CheckTimeWebsiteWasOpenedController AmmountOftimeWebsiteController;
    CheckIfPostRequestWasDoneController CheckPostRequestController;
    CheckTimePostRequestWasDone CheckTimesPostRequestController;
    int counter;

    //method for launching an instance of a webdriver
    public void OpenWeb() {

        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://www.scanmalta.com/shop/");
        driver.manage().window().maximize();

    }

    //method for searching for an item accepting a String Item
    public void SearchItem(String Item){

        OpenWeb();
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(Item);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    //method for closing the wev
    public void teardown(){
        driver.quit();
    }

    public List<WebElement> returnAllProducts()
    {

        List<WebElement> Allproducts = new LinkedList<>();

        Allproducts = driver.findElements(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[5]/div[2]/ol/li"));

        return Allproducts;
    }

    // Scraping the web in order to values from the website
    public List<Product> getAllProductInformation(List<WebElement> productList, int numberOfAlerts, int alertType) throws IOException {
        String name;
        String description;
        String url;
        String image;
        String price;
        String PostedBy;


        List<Product> productsList = new LinkedList<>();

        for(int i = 0; i < numberOfAlerts; i++)
        {
                // returning by findElement
                name = "SmartPhone results";
                description = productList.get(i).findElement(By.className("product-item-link")).getText();
                url = productList.get(i).findElement(By.className("product-item-link")).getAttribute("href");
                image = productList.get(i).findElement(By.className("product-image-photo")).getAttribute("src");
                price = productList.get(i).findElement(By.className("price")).getText();
                PostedBy = "65c4902d-14bc-43af-87e0-f5e737ea8333";

                //adding to the products List
                productsList.add(new Product(alertType, name, description, url, image, PostedBy, PriceInCents(price)));



        }

        return productsList;
    }//end of method



    public int PriceInCents(String PriceInEuro){

        int PriceInCents;
        double PriceInCent;

        // replacing the euro symbol in order to get the value of the price
        PriceInEuro = PriceInEuro.replaceAll("€", "");

        PriceInCent = Double.valueOf(PriceInEuro);
        PriceInCent = PriceInCent * 100;
        PriceInCents = (int) PriceInCent;

        return PriceInCents;

    }// end of method PriceInCents

    // method to convert to JSONStrings
    public String JSONString(Product productList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String JsonString = mapper.writeValueAsString(productList);


        //streaming the JSON value into a file
        System.out.println(JsonString);
        mapper.writeValue(new File("C:\\Users\\Owner\\Desktop\\JSON\\data.json"), productList);

        return JsonString;
    }

    //Post request in order to post the values to market alert Um
    public int postRequest2(String productJsonString) throws IOException
    {
        URL url = new URL("https://api.marketalertum.com/Alert");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        byte [] out = productJsonString.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        int responseCode = http.getResponseCode();
        System.out.print(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        return responseCode;
    }

    //method to delete alerts from market alert um

    public int deleteRequest() throws IOException {

        URL url = new URL("https://api.marketalertum.com/Alert?userId=65c4902d-14bc-43af-87e0-f5e737ea8333");
        HttpURLConnection httpCon = (HttpURLConnection)url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty(
                "Content-Type", "application/json");
        httpCon.setRequestMethod("DELETE");
        int responseCode = httpCon.getResponseCode();
        httpCon.connect();
        return  responseCode;
    }

    // ---------------------- Setter injections --------------------------

   public void setWetherWebsiteWasOpenedController(CheckIfWebsiteWasOpenedController CheckWebController)
   {
       this.CheckWebController = CheckWebController;
   }

   public void setTimesWebsiteWasOpenedController(CheckTimeWebsiteWasOpenedController AmmountOftimeWebsiteController)
   {
       this.AmmountOftimeWebsiteController = AmmountOftimeWebsiteController;
   }

   public void setWhetherPostRequestWasDone(CheckIfPostRequestWasDoneController CheckPostRequestController)
   {
       this.CheckPostRequestController = CheckPostRequestController;
   }

   public void setTimesPostRequestWasDone(CheckTimePostRequestWasDone CheckTimesPostRequestController)
   {
       this.CheckTimesPostRequestController = CheckTimesPostRequestController;
   }

   // -------------------- get --------------------------


   public boolean getResultsCheckIfPageOpened()
   {
       if(!CheckWebController.CheckIfWebsiteWasOpened()){
           return false;
       }

       return true;
   }

   public int getResultsTimeWebsiteOpened()
   {

       if(AmmountOftimeWebsiteController != null)
       {
           counter = AmmountOftimeWebsiteController.CheckIfWebsiteWasOpened();
       }

       return counter;
   }

   public int getTimesPostRequestMade()
   {
       if(CheckTimesPostRequestController != null)
       {
           counter = CheckTimesPostRequestController.MakePostRequest();
       }

       return counter;
   }
}
