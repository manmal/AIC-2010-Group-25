package aic2010.minidb;

import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Item;
import aic2010.model.Order;
import aic2010.model.Product;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 * @author Alex
 */
public class MiniDB {

    private long addressID = 0;
    private long customerID = 0;
    private long orderID = 0;
    private long productID = 0;

    private WeakHashMap<String, Address> addresses;
    private HashMap<String, Customer> customers;
    private HashMap<String, Item> items;
    private HashMap<String, Order> orders;
    private HashMap<String, Product> products;

    private MiniDB() {
    }

    public static MiniDB getInstance() {
        return MiniDBHolder.INSTANCE;
    }

    private static class MiniDBHolder {
        private static final MiniDB INSTANCE = new MiniDB();
    }

    //how to handle id?? -> use Order and Product ID combination?
    public synchronized boolean addItem(Item item)
    {
        return false;
    }

    //this is a prototype but I'm not satisfied with it. I'll change it since it has a memory leak...
    public synchronized void storeCustomer(Customer customer)
    {
        String id = customer.getId();

        if (id != null && customers.containsKey(id))
        {
            Customer actual = customers.get(id);
            actual.setName(customer.getName());
            actual.setOpenBalance(customer.getOpenBalance());
            actual.setAddresses(customer.getAddresses());
            actual.setOrders(customer.getOrders());
        }
        else
        {
            customer.setId(Long.toString(++customerID));
            customers.put(customer.getId(), customer);

            for (Address address: customer.getAddresses())
            {
                storeAddress(address);
            }

            for (Order order: customer.getOrders())
            {
                storeOrder(order);
            }
        }


    }

    public synchronized void storeOrder(Order order)
    {
        
    }

    public synchronized void storeAddress(Address address)
    {
        String id = address.getId();

        if (id != null && addresses.containsKey(id))
        {
            //if object exists, then just overwrite its properties
            Address actual = addresses.get(id);
            actual.setBilling(address.isBilling());
            actual.setCity(address.getCity());
            actual.setDoor(address.getDoor());
            actual.setHouse(address.getHouse());
            actual.setOther(address.isOther());
            actual.setShipping(address.isShipping());
            actual.setStreet(address.getStreet());
            actual.setZipCode(address.getZipCode());
        }
        else
        {
            //if object does not exist, then assign a new id and store it
            address.setId(Long.toString(++addressID));
            addresses.put(id, address);
        }
    }
 }
