package aic2010.services.test;

import aic2010.Main;
import aic2010.TestDataManager;
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
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class ShippingServiceTest {

    private Address AddressOK;
    private Address AddressMissing;
    private Product ProductMissing;
    private Customer CustomerOK;
    private Order OrderOK;
    private Order OrderWithMissingProduct;

    @Before
    public void buildTestData() {
        MiniDB.mdb().resetRunningDB();
        EmbeddedObjectContainer db = MiniDB.getDB();

        AddressOK = TestDataManager.getAddress(false, true);
        CustomerOK = TestDataManager.getCustomer(true);
        OrderOK = TestDataManager.getOrder(false, false, true, true);
        OrderWithMissingProduct = TestDataManager.getOrder(true, false, true, true);

        db.store(OrderOK);
        db.store(AddressOK);
        db.store(OrderWithMissingProduct);
        db.commit();

        // set wrong relationships for tests which shall fail:

        AddressMissing = TestDataManager.getAddress(true, true);
        ProductMissing = TestDataManager.getProduct(true, true);
        OrderWithMissingProduct.getItems().get(0).setProduct(ProductMissing);

        // rollback db to avoid changes on wrong relationships being saved:
        db.rollback();
    }

    @Test
    public void testShipItems() {
        ShippingServiceClient client = new ShippingServiceClient();
        ShippingService shippingService = client.getShippingPT();

        try {
            shippingService.shipItems(OrderOK.getItems().toArray(new Item[0]), AddressOK);
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
            shippingService.shipItems(OrderWithMissingProduct.getItems().toArray(new Item[0]), AddressOK);
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
            shippingService.shipItems(OrderOK.getItems().toArray(new Item[0]), AddressMissing);
            Assert.assertTrue(false);
        } catch (UnknownAddressException ex) {
            // we expect the unknown address exception!
             Assert.assertTrue(true);
        } catch (UnknownProductException ex) {             
             Assert.assertTrue(false);
        }
    }

}
