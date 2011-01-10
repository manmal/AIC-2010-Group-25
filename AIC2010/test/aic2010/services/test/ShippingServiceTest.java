package aic2010.services.test;

import aic2010.TestDataManager;
import aic2010.exception.UnknownAddressException;
import aic2010.exception.UnknownProductException;
import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Item;
import aic2010.model.Order;
import aic2010.model.Product;
import aic2010.services.ShippingService;
import at.ac.tuwien.infosys.aic10.ass1.dto.shipping.ShippingServiceClient;
import junit.framework.Assert;
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
        AddressOK = TestDataManager.getAddress(false, false);
        CustomerOK = TestDataManager.getCustomer(false);
        OrderOK = TestDataManager.getOrder(false, false, false, false);
        OrderWithMissingProduct = TestDataManager.getOrder(true, false, false, false);

        // set wrong relationships for tests which shall fail:

        AddressMissing = TestDataManager.getAddress(true, false);
        ProductMissing = TestDataManager.getProduct(true, false);
        OrderWithMissingProduct.getItems().get(0).setProduct(ProductMissing);
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
