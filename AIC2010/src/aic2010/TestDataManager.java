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
import java.util.List;

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
        Product product = Factory.createProduct(null, "Test Product 2", new BigDecimal(99));
        product.setId(null);

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
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        Customer customer = null;
        if(!missingCustomer)
            customer = getCustomer(false);
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


    public static List<Customer> getCustomers(boolean nullify)
    {
        List<Customer> customers = new ArrayList<Customer>();
        List<Address> list;

        Customer c1 = Factory.createCustomer("Customer1", BigDecimal.valueOf(1), null, null);
        Address a1 = Factory.createAddress("Vienna1", "1234", "Googlegasse", 20, 8, true, true, false);
        list = new ArrayList<Address>();
        list.add(a1);
        c1.setAddresses(list);
        customers.add(c1);

        Customer c2 = Factory.createCustomer("Customer2", BigDecimal.valueOf(17.3), null, null);
        Address a2 = Factory.createAddress("Vienna2", "1010", "Stephansdomplatzl", 1, 1, true, false, false);
        Address a3 = Factory.createAddress("Vienna3", "1230", "Karlsteich", 3, 0, false, true, false);
        list = new ArrayList<Address>();
        list.add(a2);
        list.add(a3);
        c2.setAddresses(list);
        customers.add(c2);

        Customer c3 = Factory.createCustomer("Customer3", BigDecimal.valueOf(-25.7), null, null);
        Address a4 = Factory.createAddress("Vienna4", "1110", "Googlegasse", 20, 8, false, false, true);
        list = new ArrayList<Address>();
        list.add(a4);
        c3.setAddresses(list);
        customers.add(c3);

        if (nullify)
        {
            c1.setId(null);
            c2.setId(null);
            c3.setId(null);
            a1.setId(null);
            a2.setId(null);
            a3.setId(null);
            a4.setId(null);
        }

        return customers;
    }

    public static void saveTestDataCustomerService() {
        EmbeddedObjectContainer db = MiniDB.getDB();

        List<Customer> customers = TestDataManager.getCustomers(false);

        for (Customer customer: customers)
        {
            db.store(customer);
        }

        db.commit();
    }

    public static void saveTestDataShippingService() {
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

    public static void resetRunningDB()
    {
        MiniDB.mdb().resetRunningDB();
    }
   
}
