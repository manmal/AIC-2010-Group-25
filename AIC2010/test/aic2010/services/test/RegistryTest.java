/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.Main;
import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.services.RegistryService;
import at.ac.tuwien.infosys.aic10.ass1.dto.registry.RegistryClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

/**
 *
 * @author rudolf
 */
public class RegistryTest {

    private static RegistryClient client;
    private static RegistryService registry;


    @Before
    public void setUp() {
        client = new RegistryClient();
        registry = client.getRegistryPT();
    }

    @Test
    public void testFindProduct1Supplier1()
    throws UnknownProductException{
        Product product = TestDataManager.getProduct(false, true);

        EndpointReferenceType endpoint = registry.findSupplier(product);

        Assert.assertNotNull(endpoint);
        Assert.assertEquals("", Main.BASE_URL+Main.SUPPLIER_SERVICE1_URL, endpoint.getAddress().getValue());
    }

    @Test
    public void testFindProduct2Supplier2()
    throws UnknownProductException{
        Product product = TestDataManager.getProduct2();

        EndpointReferenceType endpoint = registry.findSupplier(product);
        Assert.assertNotNull(endpoint);
        Assert.assertEquals("", Main.BASE_URL+Main.SUPPLIER_SERVICE2_URL, endpoint.getAddress().getValue());
    }

    @Test(expected=UnknownProductException.class)
    public void testFindMissingProduct()
    throws UnknownProductException{
        Product missingProduct = TestDataManager.getProduct(true, false);

        registry.findSupplier(missingProduct);
    }

}