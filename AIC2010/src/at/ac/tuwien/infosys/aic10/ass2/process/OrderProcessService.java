
/*
 * 
 */

package at.ac.tuwien.infosys.aic10.ass2.process;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.3
 * Fri Jan 14 12:10:01 CET 2011
 * Generated source version: 2.2.3
 * 
 */


@WebServiceClient(name = "OrderProcessService", 
                  wsdlLocation = "file:../lab2_25_nemesis/src/OrderProcess.wsdl",
                  targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass2/process") 
public class OrderProcessService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://infosys.tuwien.ac.at/aic10/ass2/process", "OrderProcessService");
    public final static QName OrderProcessPort = new QName("http://infosys.tuwien.ac.at/aic10/ass2/process", "OrderProcessPort");
    static {
        URL url = null;
        try {
            url = new URL("file:../lab2_25_nemesis/src/OrderProcess.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:../lab2_25_nemesis/src/OrderProcess.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public OrderProcessService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OrderProcessService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrderProcessService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns OrderProcessPortType
     */
    @WebEndpoint(name = "OrderProcessPort")
    public OrderProcessPortType getOrderProcessPort() {
        return super.getPort(OrderProcessPort, OrderProcessPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrderProcessPortType
     */
    @WebEndpoint(name = "OrderProcessPort")
    public OrderProcessPortType getOrderProcessPort(WebServiceFeature... features) {
        return super.getPort(OrderProcessPort, OrderProcessPortType.class, features);
    }

}
