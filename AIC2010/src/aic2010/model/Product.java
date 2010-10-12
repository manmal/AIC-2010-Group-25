package aic2010.model;

import java.math.BigDecimal;
import java.util.List;

public class Product {

    private String id;
    private String name;
    private BigDecimal singleUnitPrice;

    private List<Item> items;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getSingleUnitPrice()
    {
        return singleUnitPrice;
    }

    public void setSingleUnitPrice(BigDecimal singleUnitPrice)
    {
        this.singleUnitPrice = singleUnitPrice;
    }
}
