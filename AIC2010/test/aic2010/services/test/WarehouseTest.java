/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.model.WarehouseAnswer;
import aic2010.services.WarehouseService;
import at.ac.tuwien.infosys.aic10.ass1.dto.warehouse.WarehouseServiceClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rudolf
 */
public class WarehouseTest {

    private static WarehouseServiceClient client;
    private static WarehouseService warehouse;


    @Before
    public void setUp() {
        client = new WarehouseServiceClient();
        warehouse = client.getWarehousePT();
    }

    @Test
    public void checkKnownAvailableProduct()
    throws UnknownProductException{
        Product product1 = TestDataManager.getProduct(false, true);

        WarehouseAnswer answer = warehouse.check_availability(product1, 1);

        Assert.assertEquals("Delivery time is not correct", 2, answer.getDeliveryTime());
        Assert.assertEquals("Availability is not correct", true, answer.isIsAvailable());
    }

    @Test
    public void checkProductTooHighQuantity()
    throws UnknownProductException{
        Product product1 = TestDataManager.getProduct(false, true);

        WarehouseAnswer answer = warehouse.check_availability(product1, 4);

        Assert.assertEquals("Delivery time is not correct", 2, answer.getDeliveryTime());
        Assert.assertEquals("Availability is not correct", false, answer.isIsAvailable());
    }

    @Test
    public void checkKnownUnavailableProduct()
    throws UnknownProductException{
        Product product2 = TestDataManager.getProduct2();

        WarehouseAnswer answer = warehouse.check_availability(product2, 1);

        Assert.assertEquals("Delivery time is not correct", 2, answer.getDeliveryTime());
        Assert.assertEquals("Availability is not correct", true, answer.isIsAvailable());
    }

    @Test(expected=UnknownProductException.class)
    public void checkUnknownProduct()
    throws UnknownProductException{
        Product unknownProduct = TestDataManager.getProduct(true, false);

        //should throw an UnknownProductException
        warehouse.check_availability(unknownProduct, 1);
    }
}