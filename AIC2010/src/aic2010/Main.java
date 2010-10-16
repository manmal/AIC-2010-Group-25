package aic2010;

import aic2010.services.ShippingService;
import aic2010.services.ShippingServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Endpoint;
import org.apache.cxf.endpoint.Server;

/**
 * @author manuelmaly
 */
public class Main {

    public static final String BASE_URL = "http://localhost:8088/";
    public static final String SHIPPING_SERVICE_URL = BASE_URL + "shippingservice";

    public static List<Endpoint> endpoints = new ArrayList<Endpoint>();

    public static void main(String[] args) {
        startShippingService();
    }

    public static void startShippingService() {
        ShippingService shippingService = new ShippingServiceImpl();
        endpoints.add(Endpoint.publish(SHIPPING_SERVICE_URL, shippingService));
    }

    public static void stopAllServices() {
        for(Endpoint point : endpoints) {
            point.stop();
        }
    }

}
