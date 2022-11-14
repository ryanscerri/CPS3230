package MarketAlertUmAssignment.PageObjects;

public class Product {

    //Local Variables
    String heading;
    String description;
    String url;
    String imageUrl;
    int priceInCents;
    String postedBy;
    int alertType;


       //constructor for Product
        public Product(int alertType, String heading, String description, String url, String imageUrl, String postedBy, int priceInCents)
        {
            this.alertType = alertType;
            this.heading = heading;
            this.description = description;
            this.url = url;
            this.imageUrl = imageUrl;
            this.postedBy = postedBy;
            this.priceInCents = priceInCents;


        }

    }


