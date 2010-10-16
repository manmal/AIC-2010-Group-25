/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.Main;
import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Item;
import aic2010.model.Order;
import aic2010.model.Product;
import aic2010.services.ShippingService;
import at.ac.tuwien.infosys.aic10.ass1.dto.shipping.ShippingServiceClient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.junit.Test;

/**
 *
 * @author manuelmaly
 */
public class ShippingServiceTest {

    @Test
    public void testShipItems() {
        Main.startShippingService();

        ShippingServiceClient client = new ShippingServiceClient();
        ShippingService shippingService = client.getShippingPT();

        Address address = new Address();
        address.setCity("Test City");
        address.setDoor(4);
        address.setHouse(10);
        address.setId(UUID.randomUUID().toString());
        address.setStreet("Test Street");
        address.setZipCode("2890");

        Customer customer = new Customer();
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(new Date());

        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Test Product");
        product.setSingleUnitPrice(BigDecimal.ZERO);

        Item item = new Item();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(2);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        order.setItems(items);
        
        shippingService.shipItems(items.toArray(new Item[0]), address);
    }
}
