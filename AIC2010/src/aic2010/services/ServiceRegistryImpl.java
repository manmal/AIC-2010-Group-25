/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.Main;
import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.registry.AttributedURI;
import aic2010.registry.EndpointReferenceType;
import aic2010.registry.ObjectFactory;
import aic2010.registry.ServiceNameType;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */
@WebService(endpointInterface = "aic2010.services.ServiceRegistry",
            serviceName = "ServiceRegistry",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/registry",
            portName="RegistryPT")
public class ServiceRegistryImpl implements ServiceRegistry {

    private Map<Product, EndpointReferenceType> suppliers;


    public ServiceRegistryImpl(){
        suppliers = new HashMap<Product, EndpointReferenceType>();
        addSuppliers();
    }

    @Override
    public EndpointReferenceType findSupplier(Product product)
    throws UnknownProductException{
        if (suppliers.containsKey(product)){
            return suppliers.get(product);
        }
        else{
            throw new UnknownProductException("Could not find product", product.getName());
        }
    }

    private void addSuppliers(){
        ObjectFactory factory = new ObjectFactory();
        EndpointReferenceType service1 = factory.createEndpointReferenceType();
        EndpointReferenceType service2 = factory.createEndpointReferenceType();

        ServiceNameType serviceName = factory.createServiceNameType();
        serviceName.setPortName("SupplierPT");

        AttributedURI service1Uri = factory.createAttributedURI();
        service1Uri.setValue(Main.BASE_URL+Main.SUPPLIER_SERVICE1_URL);
        AttributedURI service2Uri = factory.createAttributedURI();
        service1Uri.setValue(Main.BASE_URL+Main.SUPPLIER_SERVICE2_URL);

        service1.setServiceName(serviceName);
        service2.setServiceName(serviceName);
        service1.setAddress(service1Uri);
        service2.setAddress(service2Uri);

        Product product1 = TestDataManager.getProduct(false, true);
        suppliers.put(product1, service1);

        Product product2 = TestDataManager.getProduct2();
        suppliers.put(product2, service2);
    }

}
