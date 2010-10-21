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
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxws.EndpointImpl;

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

        /*
        //create rest server bean
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(CustomerManagement.class);
        sf.setResourceProvider(CustomerManagement.class,
            new SingletonResourceProvider(new CustomerManagement()));
        sf.setAddress(BASE_URL);

        //create binding factory for rest server
        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(sf.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);

        //create server
        sf.create();
        */

        
        //create soap service
        CustomerService customerService = new CustomerServiceImpl();
        EndpointImpl ep = (EndpointImpl)Endpoint.publish(CUSTOMER_SERVICE_URL, customerService);

        //add logging interceptor
        ep.getServer().getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        ep.getServer().getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());
        
        endpoints.add(ep);
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
