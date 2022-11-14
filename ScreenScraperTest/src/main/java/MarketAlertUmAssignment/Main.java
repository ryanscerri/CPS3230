package MarketAlertUmAssignment;

import MarketAlertUmAssignment.PageObjects.Product;
import MarketAlertUmAssignment.PageObjects.SearchResults;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    public static void main(String args[]) throws IOException {
        SearchResults dfg = new SearchResults();
        List<Product> products = new LinkedList<>();

        dfg.SearchItem("Iphone");

        List<WebElement> searchResults = new LinkedList<>();

        searchResults = dfg.returnAllProducts();

        products = dfg.getAllProductInformation(searchResults, 5, 6);

        for (Product product: products) {

            String JsonString = dfg.JSONString(product);
            dfg.postRequest2(JsonString);
        }

//        int responseCode = dfg.deleteRequest();
//        System.out.println(responseCode);







        dfg.teardown();
    }
}
