package aic2010.model;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)   //serialize all fields by default
@XmlRootElement(name="customer")         //name root element
public class Customer {

    //serialize this id as attribute
    @XmlAttribute(required=true)
    private String id;
    
    private String name;
    private BigDecimal openBalance;

    private List<Order> orders;
    private List<Address> addresses;

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

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Customer other = (Customer) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id))
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        if (this.openBalance != other.openBalance && (this.openBalance == null || !this.openBalance.equals(other.openBalance)))
        {
            return false;
        }
        if (this.orders != other.orders && (this.orders == null || !this.orders.equals(other.orders)))
        {
            return false;
        }
        if (this.addresses != other.addresses && (this.addresses == null || !this.addresses.equals(other.addresses)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.openBalance != null ? this.openBalance.hashCode() : 0);
        hash = 97 * hash + (this.orders != null ? this.orders.hashCode() : 0);
        hash = 97 * hash + (this.addresses != null ? this.addresses.hashCode() : 0);
        return hash;
    }
}
