package at.ac.tuwien.infosys.aic10.ass1.dto.customerservice;

import aic2010.Main;
import aic2010.services.CustomerService;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

@WebServiceClient(name = "CustomerService",
                  wsdlLocation = Main.CUSTOMER_SERVICE_URL + "?wsdl",
                  targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/customer")
public class CustomerServiceClient extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/customer", "CustomerService");
    public final static QName CustomerPT = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/customer", "CustomerPT");
    static {
        URL url = null;
        try {
            url = new URL(Main.CUSTOMER_SERVICE_URL + "?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from " + Main.CUSTOMER_SERVICE_URL + "?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public CustomerServiceClient(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CustomerServiceClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CustomerServiceClient() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "CustomerPT")
    public CustomerService getCustomerPT() {
        return super.getPort(CustomerPT, CustomerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "CustomerPT")
    public CustomerService getCustomerPT(WebServiceFeature... features) {
        return super.getPort(CustomerPT, CustomerService.class, features);
    }

}
