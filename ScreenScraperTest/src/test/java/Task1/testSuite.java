package Task1;


import MarketAlertUmAssignment.PageObjects.Product;
import MarketAlertUmAssignment.PageObjects.SearchResults;
import Task1.Spy.TimePostReqDoneControllerSpy;
import Task1.Spy.TimeWebsiteIsOpenedControllerSpy;
import Task1.Stubs.WebsiteNotOpened;
import Task1.Stubs.postRequestNotDone;
import Task1.Stubs.postRequestsDone;
import Task1.Stubs.websiteOpened;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class testSuite {



    @Test
    public void CheckIfWebsiteOpened(){

        //Setup
        SearchResults SR = new SearchResults();
        SR.setWetherWebsiteWasOpenedController(new websiteOpened());

        //Exersise
        boolean result = SR.getResultsCheckIfPageOpened();

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void CheckIfWebsiteNotOpened(){

        //Setup
        SearchResults SR = new SearchResults();
        SR.setWetherWebsiteWasOpenedController(new WebsiteNotOpened());

        //Exersise
        boolean result = SR.getResultsCheckIfPageOpened();

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void CheckIfPostRequestIsDone() throws IOException {

        //Setup
        SearchResults SR = new SearchResults();
        SR.setWhetherPostRequestWasDone(new postRequestsDone());

        //Exersise
        int responseCode = SR.postRequest2("{\"heading\":\"SmartPhone\",\"description\":\"BeHello iPhone 14 Plus High Impact Glass Screen\",\"url\":\"https://www.scanmalta.com/shop/behello-iphone-14-plus-high-impact-glass-screen-ap.html\",\"imageUrl\":\"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/1/e/1e19ac16-1ec9-4804-a3b1-4db14a750e88_ip14_6-7-282-29.jpg\",\"priceInCents\":1995,\"postedBy\":\"65c4902d-14bc-43af-87e0-f5e737ea8333\",\"alertType\":6}");

        //Verify
        Assertions.assertEquals(201, responseCode);
    }

    @Test
    public void CheckIfPostRequestIsNotDone() throws IOException {

        //Setup
        SearchResults SR = new SearchResults();
        SR.setWhetherPostRequestWasDone(new postRequestNotDone());

        //Exersise
        int responseCode = SR.postRequest2("");

        //Verify
        Assertions.assertEquals(400, responseCode);
    }

    @Test
    public void CheckIfDeleteRequestIsDone() throws IOException {
        //Setup
        SearchResults SR = new SearchResults();

        //Exercise
        int responseCode = SR.deleteRequest();

        //Verify
        Assertions.assertEquals(200, responseCode);
    }



    @Test
    public void testPriceToCents()
    {
        //Setup
        SearchResults SR = new SearchResults();

        //Exercise
        int Price = SR.PriceInCents("€10.00");

        //Verify
        Assertions.assertEquals(1000, Price);

    }

    @Test
    public void TestJsonConversion() throws IOException
    {
        //Setup
        Product P = new Product(6, "SmartPhone", "BeHello iPhone 14 Plus High Impact Glass Screen", "https://www.scanmalta.com/shop/behello-iphone-14-plus-high-impact-glass-screen-ap.html", "https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/1/e/1e19ac16-1ec9-4804-a3b1-4db14a750e88_ip14_6-7-282-29.jpg", "65c4902d-14bc-43af-87e0-f5e737ea8333", 1995);
        SearchResults SR = new SearchResults();

        //Exercise
        String JsonString = SR.JSONString(P);

        //Verify
       Assertions.assertEquals("{\"heading\":\"SmartPhone\",\"description\":\"BeHello iPhone 14 Plus High Impact Glass Screen\",\"url\":\"https://www.scanmalta.com/shop/behello-iphone-14-plus-high-impact-glass-screen-ap.html\",\"imageUrl\":\"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/1/e/1e19ac16-1ec9-4804-a3b1-4db14a750e88_ip14_6-7-282-29.jpg\",\"priceInCents\":1995,\"postedBy\":\"65c4902d-14bc-43af-87e0-f5e737ea8333\",\"alertType\":6}", JsonString);
    }


    @Test
    public void TestOnePageOpened() {
        //Setup
        TimeWebsiteIsOpenedControllerSpy timeOpenedControllerSpy = new TimeWebsiteIsOpenedControllerSpy();
        SearchResults sr = new SearchResults();
        sr.setTimesWebsiteWasOpenedController(timeOpenedControllerSpy);

        //Exercise
        int counter = sr.getResultsTimeWebsiteOpened();

        //Verify
        Assertions.assertEquals(1, counter);
    }

    @Test
    public void TestPostReqDone() {
        //Setup
        TimePostReqDoneControllerSpy timeOpenedControllerSpy = new TimePostReqDoneControllerSpy();
        SearchResults sr = new SearchResults();
        sr.setTimesPostRequestWasDone(timeOpenedControllerSpy);

        //Exercise
        int counter = sr.getTimesPostRequestMade();

        //Verify
        Assertions.assertEquals(1, counter);
    }




}
