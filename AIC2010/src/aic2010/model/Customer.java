package aic2010.model;

import java.math.BigDecimal;
import java.util.List;

public class Customer {

    private String id;
    
    private String name;
    private BigDecimal openBalance;

    private List<Order> orders;
    private List<Addresses> addresses;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getOpenBalance()
    {
        return openBalance;
    }

    public void setOpenBalance(BigDecimal openBalance)
    {
        this.openBalance = openBalance;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    public List<Addresses> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Addresses> addresses)
    {
        this.addresses = addresses;
    }
}
