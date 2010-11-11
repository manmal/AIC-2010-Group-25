/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.services.SupplierService;
import at.ac.tuwien.infosys.aic10.ass1.dto.supplier.SupplierServiceClient;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rudolf
 */
public class SupplierTest {

    private static SupplierServiceClient cs = null;
    private static SupplierService supplier;


    @Before
    public void setUp() {
    //        cs = new SupplierServiceClient();
//        supplier = cs.getSupplierPT();
//        ShippingServiceClient client = new ShippingServiceClient();
//        ShippingService shippingService = client.getShippingPT();
        cs = new SupplierServiceClient();
        supplier = cs.getSupplierPT();
    }
    @Test
    public void orderProduct()
    throws UnknownProductException{

        Product knownProduct = TestDataManager.getProduct2();

        BigDecimal expectedPrice = knownProduct.getSingleUnitPrice();
//        BigDecimal supplierPrice = supplier.order(knownProduct, 1);

//        Assert.assertEquals("Returned price was not correct", expectedPrice, supplierPrice);
    }

    @Test
    public void orderMultipleProducts()
    throws UnknownProductException{
        Product knownProduct = TestDataManager.getProduct2();

        BigDecimal expectedPrice = knownProduct.getSingleUnitPrice().multiply(new BigDecimal(3));
        BigDecimal supplierPrice = supplier.order(knownProduct, 3);

        Assert.assertEquals("Returned price was not correct", expectedPrice, supplierPrice);
    }

    @Test(expected=UnknownProductException.class)
    public void orderUnknownProduct()
    throws UnknownProductException{
        Product unknownProduct = TestDataManager.getProduct(true, false);

        //should throw an UnknownProductException
        supplier.order(unknownProduct, 3);
    }
}