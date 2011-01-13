/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.Main;
import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import org.xmlsoap.schemas.ws._2004._08.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

/**
 *
 * @author rudolf
 */
@WebService(endpointInterface = "aic2010.services.RegistryService",
            serviceName = "RegistryService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/registry",
            portName="RegistryPT")
public class RegistryServiceImpl implements RegistryService {

    private Map<String, String> endpoints;


    public RegistryServiceImpl(){
        endpoints = new HashMap<String, String>();
        addSuppliers();
    }

    @Override
    public EndpointReferenceType findSupplier(Product product)
    throws UnknownProductException{
        if (endpoints.containsKey(product.getName())){
            String address = endpoints.get(product.getName());
            AttributedURI uri = new AttributedURI();
            uri.setValue(address);

            EndpointReferenceType endpoint = new EndpointReferenceType();
            endpoint.setAddress(uri);

            return endpoint;
            
        }
        else{
            throw new UnknownProductException("Could not find product", product.getName());
        }
    }

    private void addSuppliers(){
        AttributedURI service1Uri = new AttributedURI();
        String uri1 = Main.BASE_URL+Main.SUPPLIER_SERVICE1_URL;
        AttributedURI service2Uri = new AttributedURI();
        String uri2 = Main.BASE_URL+Main.SUPPLIER_SERVICE2_URL;
        
//        Product product1 = TestDataManager.getProduct(false, false);
//        endpoints.put(product1.getName(), uri1);
//
//        Product product2 = TestDataManager.getProduct2();
//        endpoints.put(product2.getName(), uri2);
    }

}
