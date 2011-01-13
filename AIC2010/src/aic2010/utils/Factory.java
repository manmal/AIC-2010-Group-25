package aic2010.utils;

import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Item;
import aic2010.model.Order;
import aic2010.model.Product;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Factory {

    private static Integer product_id = 0;
    private static Integer customer_id = 0;
    private static Integer address_id = 0;
    private static Integer order_id = 0;

    public synchronized static Customer createCustomer(String name, BigDecimal openBalance, List<Address> addresses, List<Order> orders)
    {

        Customer customer = new Customer();
        customer_id += 1;
        customer.setId(customer_id.toString());
        customer.setName(name);
        customer.setOpenBalance(openBalance);
        customer.setAddresses(addresses);
        customer.setOrders(orders);

        System.out.println("Customer with ID " + customer_id);
        return customer;
    }

    public synchronized static Order createOrder(Customer customer, List<Item> items, Date orderDate) {
        Order order = new Order();
        //order.setId(UUID.randomUUID().toString());
        order_id +=1;
        order.setId(order_id.toString());
        order.setCustomer(customer);
        order.setItems(items);
        order.setOrderDate(orderDate);

        System.out.println("Order with ID " + order_id);
        return order;
    }

    public synchronized static Item createItem(Order order, Product product, int quantity) {
        Item item = new Item();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);

        return item;
    }

    public synchronized static Product createProduct(List<Item> items, String name, BigDecimal unitPrice) {
        Product product = new Product();
        product_id+=1;
        //product.setId(UUID.randomUUID().toString());
        product.setId(product_id.toString());
        product.setItems(items);
        product.setName(name);
        product.setSingleUnitPrice(unitPrice);

        System.out.println("Product with ID " + product_id);
        return product;
    }

    public synchronized static Address createAddress(String city, String zipCode, String street, int door, int house, boolean billing, boolean shipping, boolean other)
    {
        Address address = new Address();
        address_id+=1;

        //address.setId(UUID.randomUUID().toString());
        address.setId(address_id.toString());

        address.setCity(city);
        address.setZipCode(zipCode);
        address.setStreet(street);

        address.setDoor(door);
        address.setHouse(house);
        address.setBilling(billing);
        address.setShipping(shipping);
        address.setOther(other);

        System.out.println("Address with ID " + address_id);

        return address;
    }
}
