/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic10.ass1.dto.registry;

import aic2010.Main;
import aic2010.services.CustomerService;
import aic2010.services.RegistryService;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 *
 * @author rudolf
 */
@WebServiceClient(name = "RegistryService",
                  wsdlLocation=Main.REGISTRY_SERVICE_URL,
                  targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/registry")
public class RegistryClient extends Service{
    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/registry", "RegistryService");
    public final static QName RegistryPT = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/registry", "RegistryPT");

    static {
        URL url = null;
        try {
            url = new URL(Main.REGISTRY_SERVICE_URL + "?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from " + Main.REGISTRY_SERVICE_URL + "?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }
    public RegistryClient(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RegistryClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RegistryClient() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     *
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "RegistryPT")
    public RegistryService getRegistryPT() {
        return super.getPort(RegistryPT, RegistryService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "RegistryPT")
    public CustomerService getRegistryPT(WebServiceFeature... features) {
        return super.getPort(RegistryPT, CustomerService.class, features);
    }
}
