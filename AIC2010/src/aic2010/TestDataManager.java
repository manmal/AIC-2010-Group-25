/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010;

import aic2010.datastore.MiniDB;
import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Item;
import aic2010.model.Order;
import aic2010.model.Product;
import aic2010.utils.Factory;
import com.db4o.EmbeddedObjectContainer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author manuelmaly
 */
public class TestDataManager {

    public static Address getAddress(boolean missing, boolean nullifyID) {
        Address address = null;
        if(missing)
            address = Factory.createAddress("Missing Test City", "Missing ZIP", "Missing Street", -1, -1, false, false, false);
        else
            address = Factory.createAddress("Test City", "2890", "Test Street", 4, 10, false, false, false);
        if(nullifyID)
            address.setId(null);
        return address;
    }

    public static Product getProduct(boolean missing, boolean nullifyID) {
        Product product = null;
        if(missing)
            product = Factory.createProduct(null, "Faulty Test Product", BigDecimal.ZERO);
        else
            product =  Factory.createProduct(null, "Test Product 1", BigDecimal.ZERO);
        if(nullifyID)
            product.setId(null);
        return product;
    }

    public static Product getProduct2(){
        Product product = Factory.createProduct(null, "Test Product 2", BigDecimal.ZERO);

        return product;
    }
    
    public static Customer getCustomer(boolean nullifyID) {
        Customer customer = Factory.createCustomer("Any Andy", BigDecimal.ZERO, null, null);
        if(nullifyID)
            customer.setId(null);
        return customer;
    }

    public static Order getOrder(boolean missingProductInItem, boolean missingCustomer, boolean nullifyOrderID, boolean nullifyProductID) {
        Item item = getItemWithoutOrder(missingProductInItem, nullifyProductID);
        ArrayList<Item> items = new ArrayList();
        items.add(item);
        Customer customer = null;
        if(!missingCustomer)
            customer = getCustomer(true);
        Order order = Factory.createOrder(customer, items, new Date());
        if(nullifyOrderID)
            order.setId(null);
        return order;
    }

    public static Item getItemWithoutOrder(boolean missingProduct, boolean nullifyProductID) {
        Product product = null;
        if(!missingProduct) {
            product = Factory.createProduct(null, "Test Product", BigDecimal.ZERO);
            if(nullifyProductID)
                product.setId(null);
        }
        return Factory.createItem(null, product, 2);
    }

    public static void saveTestDataShippingService() {
        MiniDB.mdb().resetRunningDB();
        EmbeddedObjectContainer db = MiniDB.getDB();

        Address AddressOK = TestDataManager.getAddress(false, false);
        Customer CustomerOK = TestDataManager.getCustomer(false);
        Order OrderOK = TestDataManager.getOrder(false, false, false, false);
        Order OrderWithMissingProduct = TestDataManager.getOrder(true, false, false, false);

        db.store(OrderOK);
        db.store(AddressOK);
        db.store(OrderWithMissingProduct);
        db.commit();
    }
   
}
