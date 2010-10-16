package aic2010;

import aic2010.services.ShippingService;
import aic2010.services.ShippingServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import org.mortbay.jetty.Server;

/**
 * @author manuelmaly
 */
public class Main {

    public static void main(String[] args) {
        ShippingService shippingService = new ShippingServiceImpl();
        String shippingServiceAddress = "http://localhost:8088/shipping";
        Endpoint.publish(shippingServiceAddress, shippingService);
    }

}
