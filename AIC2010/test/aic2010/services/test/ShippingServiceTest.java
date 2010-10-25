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

        CustomerOK = Factory.createCustomer("Any Andy", BigDecimal.ZERO, null, null);
        CustomerOK.setId(UUID.randomUUID().toString());

        OrderOK = Factory.createOrder(CustomerOK, null, new Date());
        OrderOK.setId(UUID.randomUUID().toString());

        ProductOK = Factory.createProduct(null, "Test Product", BigDecimal.ZERO);
        ProductOK.setId(UUID.randomUUID().toString());

        ItemOK = Factory.createItem(OrderOK, ProductOK, 2);

        ItemsOK = new ArrayList<Item>();
        ItemsOK.add(ItemOK);
        OrderOK.setItems(ItemsOK);

        db.store(OrderOK);
        db.store(AddressOK);

        /////// FAULTY ENTITIES ///////

        // Note that the faulty entities are not stored into the DB,
        // and will thus not be available to the WS (which is what we want)!

        OrderWithFaultyProduct = Factory.createOrder(CustomerOK, null, new Date());
        OrderWithFaultyProduct.setId(UUID.randomUUID().toString());

        // Product is faulty, which means it will not be stored in the DB
        ProductMissing = Factory.createProduct(null, "Faulty Test Product", BigDecimal.ZERO);
        ProductMissing.setId(UUID.randomUUID().toString());

        ItemWithFaultyProduct = Factory.createItem(OrderWithFaultyProduct, null, 2);

        ItemsWithMissingProduct = new ArrayList<Item>();
        ItemsWithMissingProduct.add(ItemWithFaultyProduct);
        OrderWithFaultyProduct.setItems(ItemsWithMissingProduct);

        db.store(OrderWithFaultyProduct);
        db.commit();

        // After we have stored the object graph for the faulty product order,
        // lets connect the faulty product so it is available to the test case
        // to generate an error:

        ItemWithFaultyProduct.setProduct(ProductMissing);

        AddressFaulty = Factory.createAddress("Missing Test City", "Missing ZIP", "Missing Street", -1, -1, false, false, false);
        AddressFaulty.setId(UUID.randomUUID().toString());

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
    }

    @Test
    public void testShipMissingAddress() {
        buildTestData();

        ShippingServiceClient client = new ShippingServiceClient();
        ShippingService shippingService = client.getShippingPT();
        try {
            shippingService.shipItems(ItemsOK.toArray(new Item[0]), AddressFaulty);
            Assert.assertTrue(false);
        } catch (UnknownAddressException ex) {
            // we expect the unknown address exception!
             Assert.assertTrue(true);
        } catch (UnknownProductException ex) {             
             Assert.assertTrue(false);
        }
    }

}
