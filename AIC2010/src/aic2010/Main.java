package aic2010;

import aic2010.services.ShippingService;
import aic2010.services.ShippingServiceImpl;
import javax.xml.ws.Endpoint;

/**
 * @author manuelmaly
 */
public class Main {

    public static final String BASE_URL = "http://localhost:8088/";
    public static final String SHIPPING_SERVICE_URL = BASE_URL + "shippingservice";

    public static void main(String[] args) {
        startShippingService();
    }

    public static void startShippingService() {
        ShippingService shippingService = new ShippingServiceImpl();
        Endpoint.publish(SHIPPING_SERVICE_URL, shippingService);
    }

}
