package aic2010;

import aic2010.jaxrs.CustomerManagementImpl;
import aic2010.services.CustomerService;
import aic2010.services.CustomerServiceImpl;
import aic2010.services.ShippingService;
import aic2010.services.ShippingServiceImpl;
import aic2010.services.SupplierService;
import aic2010.services.SupplierServiceImpl;
import aic2010.services.WarehouseService;
import aic2010.services.WarehouseServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Endpoint;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.endpoint.Server;
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
    public static final String REST_BASE_URL = "http://localhost:9000/";
    public static final String REST_CUSTOMER_SERVICE_URL = REST_BASE_URL + "customermanagement";
    public static final String SHIPPING_SERVICE_URL = BASE_URL + "shippingservice";
    public static final String CUSTOMER_SERVICE_URL = BASE_URL + "customerservice";
    public static final String SUPPLIER_SERVICE1_URL = BASE_URL + "supplier1";
    public static final String SUPPLIER_SERVICE2_URL = BASE_URL + "supplier2";
    public static final String WAREHOUSE_SERVICE_URL = BASE_URL + "warehouse";

    public static List<Endpoint> endpoints = new ArrayList<Endpoint>();

    public static void main(String[] args) {
        startShippingService();
        startCustomerManagementService();
        startSupplierServices();
        startWarehouseService();
    }

    public static void startCustomerManagementService() {

        //create rest server bean
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(CustomerManagementImpl.class);
        sf.setResourceProvider(CustomerManagementImpl.class,
            new SingletonResourceProvider(new CustomerManagementImpl()));
        sf.setAddress(REST_BASE_URL);

        //create binding factory for rest server
        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(sf.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);

        //create server
        Server server = sf.create();
        server.getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());
        server.getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        
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

    public static void startSupplierServices(){
        SupplierService supplier1 = new SupplierServiceImpl();
        SupplierService supplier2 = new SupplierServiceImpl();
        endpoints.add(Endpoint.publish(SUPPLIER_SERVICE1_URL, supplier1));
        endpoints.add(Endpoint.publish(SUPPLIER_SERVICE2_URL, supplier2));
    }

    public static void startWarehouseService(){
        WarehouseService warehouse = new WarehouseServiceImpl();
        endpoints.add(Endpoint.publish(WAREHOUSE_SERVICE_URL, warehouse));
    }

    public static void stopAllServices() {
        for(Endpoint point : endpoints) {
            point.stop();
        }
    }

}
