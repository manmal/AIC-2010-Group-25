/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.Main;
import aic2010.datastore.MiniDB;
import aic2010.exception.UnknownAddressException;
import aic2010.exception.UnknownProductException;
import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Item;
import aic2010.model.Order;
import aic2010.model.Product;
import aic2010.services.ShippingService;
import aic2010.utils.Factory;
import at.ac.tuwien.infosys.aic10.ass1.dto.shipping.ShippingServiceClient;
import com.db4o.EmbeddedObjectContainer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manuelmaly
 */
public class ShippingServiceTest {

    private Address AddressOK;
    private Address AddressFaulty;

    private Product ProductOK;
    private Product ProductMissing;

    private Item ItemOK;
    private ArrayList<Item> ItemsOK;
    private Item ItemWithFaultyProduct;
    private ArrayList<Item> ItemsWithMissingProduct;

    private Order OrderOK;
    private Order OrderWithFaultyProduct;
    private Order OrderWithFaultyAddress;

    private Customer CustomerOK;


    @BeforeClass
    public static void bootstrapAllTests() {
        Main.startShippingService();
    }

    @AfterClass
    public static void teardownAllTests() {
        Main.stopAllServices();
    }

    @Before
    public void buildTestData() {
        MiniDB.mdb().resetRunningDB();
        EmbeddedObjectContainer db = MiniDB.mdb().getDB();

        AddressOK = Factory.createAddress("Test City", "2890", "Test Street", 4, 10, false, false, false);
        AddressOK.setId(UUID.randomUUID().toString());

        CustomerOK = new Customer();
        OrderOK = new Order();
        OrderOK.setCustomer(CustomerOK);
        OrderOK.setOrderDate(new Date());

        ProductOK = new Product();
        ProductOK.setId(UUID.randomUUID().toString());
        ProductOK.setName("Test Product");
        ProductOK.setSingleUnitPrice(BigDecimal.ZERO);

        ItemOK = new Item();
        ItemOK.setOrder(OrderOK);
        ItemOK.setProduct(ProductOK);
        ItemOK.setQuantity(2);

        ItemsOK = new ArrayList<Item>();
        ItemsOK.add(ItemOK);
        OrderOK.setItems(ItemsOK);

        db.store(OrderOK);
        db.store(AddressOK);


        OrderWithFaultyProduct = new Order();
        OrderWithFaultyProduct.setCustomer(CustomerOK);
        OrderWithFaultyProduct.setOrderDate(new Date());

        // Product is faulty, which means it will not be stored in the DB
        ProductMissing = new Product();
        ProductMissing.setId(UUID.randomUUID().toString());
        ProductMissing.setName("Faulty Test Product");
        ProductMissing.setSingleUnitPrice(BigDecimal.ZERO);

        ItemWithFaultyProduct = new Item();
        ItemWithFaultyProduct.setOrder(OrderWithFaultyProduct);
        ItemWithFaultyProduct.setProduct(null);
        ItemWithFaultyProduct.setQuantity(2);

        ItemsWithMissingProduct = new ArrayList<Item>();
        ItemsWithMissingProduct.add(ItemWithFaultyProduct);
        OrderWithFaultyProduct.setItems(ItemsWithMissingProduct);

        db.store(OrderWithFaultyProduct);
        db.commit();

        // After we have stored the object graph for the faulty product order,
        // lets connect the faulty product so it is available to the test case
        // to generate an error:

        ItemWithFaultyProduct.setProduct(ProductMissing);

        db.rollback();
    }

    @Test
    public void testShipItems() {
        ShippingServiceClient client = new ShippingServiceClient();
        ShippingService shippingService = client.getShippingPT();

        try {
            shippingService.shipItems(ItemsOK.toArray(new Item[0]), AddressOK);
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testShipMissingProduct() {
        

        buildTestData();

        ShippingServiceClient client = new ShippingServiceClient();
        ShippingService shippingService = client.getShippingPT();
        try {
            shippingService.shipItems(ItemsWithMissingProduct.toArray(new Item[0]), AddressOK);
            Assert.assertTrue(false);
        } catch (UnknownAddressException ex) {
             Assert.assertTrue(false);
        } catch (UnknownProductException ex) {
             // we expect the unknown product exception!
             Assert.assertTrue(true);
        }
        Main.stopAllServices();
    }


}
