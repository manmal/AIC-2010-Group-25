package aic2010.utils;

import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Order;
import java.math.BigDecimal;
import java.util.List;

public class Factory {

    public static Customer createCustomer(String name, BigDecimal openBalance, List<Address> addresses, List<Order> orders)
    {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setOpenBalance(openBalance);
        customer.setAddresses(addresses);
        customer.setOrders(orders);

        return customer;
    }

    public static Address createAddress(String city, String zipCode, String street, int door, int house, boolean billing, boolean shipping, boolean other)
    {
        Address address = new Address();

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
