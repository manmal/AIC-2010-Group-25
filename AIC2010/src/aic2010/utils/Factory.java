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

    static Integer product_id = 0;
    static Integer address_id = 0;
    static Integer order_id = 0;

    public static Customer createCustomer(String name, BigDecimal openBalance, List<Address> addresses, List<Order> orders)
    {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName(name);
        customer.setOpenBalance(openBalance);
        customer.setAddresses(addresses);
        customer.setOrders(orders);

        return customer;
    }

    public static Order createOrder(Customer customer, List<Item> items, Date orderDate) {
        Order order = new Order();
        //order.setId(UUID.randomUUID().toString());
        order_id +=1;
        order.setId(order_id.toString());
        order.setCustomer(customer);
        order.setItems(items);
        order.setOrderDate(orderDate);
        return order;
    }

    public static Item createItem(Order order, Product product, int quantity) {
        Item item = new Item();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }

    public static Product createProduct(List<Item> items, String name, BigDecimal unitPrice) {
        Product product = new Product();
        product_id+=1;
        //product.setId(UUID.randomUUID().toString());
        product.setId(product_id.toString());
        product.setItems(items);
        product.setName(name);
        product.setSingleUnitPrice(unitPrice);
        return product;
    }

    public static Address createAddress(String city, String zipCode, String street, int door, int house, boolean billing, boolean shipping, boolean other)
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

        return address;
    }
}
