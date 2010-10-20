package aic2010;

import aic2010.jaxrs.CustomerManagement;
import aic2010.services.CustomerService;
import aic2010.services.CustomerServiceImpl;
import aic2010.services.ShippingService;
import aic2010.services.ShippingServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Endpoint;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

/**
 * @author manuelmaly
 */
public class Main {

    public static final String BASE_URL = "http://localhost:8088/";
    public static final String SHIPPING_SERVICE_URL = BASE_URL + "shippingservice";
    public static final String CUSTOMER_SERVICE_URL = BASE_URL + "customerservice";

    public static List<Endpoint> endpoints = new ArrayList<Endpoint>();

    public static void main(String[] args) {
        startShippingService();
        startCustomerManagementService();
    }

    public static void startCustomerManagementService() {

        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(CustomerManagement.class);
        sf.setResourceProvider(CustomerManagement.class,
            new SingletonResourceProvider(new CustomerManagement()));
        sf.setAddress("http://localhost:8088/");

        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(sf.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);

        sf.create();

        CustomerService customerService = new CustomerServiceImpl();
        endpoints.add(Endpoint.publish(CUSTOMER_SERVICE_URL, customerService));
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
