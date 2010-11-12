package at.ac.tuwien.infosys.aic10.ass1.dto.shipping;

import aic2010.Main;
import aic2010.services.ShippingService;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

@WebServiceClient(name = "ShippingService", 
                  wsdlLocation = Main.SHIPPING_SERVICE_URL + "?wsdl",
                  targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping") 
public class ShippingServiceClient extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping", "ShippingService");
    public final static QName ShippingPT = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping", "ShippingPT");
    static {
        URL url = null;
        try {
            url = new URL(Main.SHIPPING_SERVICE_URL + "?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from " + Main.SHIPPING_SERVICE_URL + "?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ShippingServiceClient(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ShippingServiceClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShippingServiceClient() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns ShippingService
     */
    @WebEndpoint(name = "ShippingPT")
    public ShippingService getShippingPT() {
        return super.getPort(ShippingPT, ShippingService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShippingService
     */
    @WebEndpoint(name = "ShippingPT")
    public ShippingService getShippingPT(WebServiceFeature... features) {
        return super.getPort(ShippingPT, ShippingService.class, features);
    }

}
