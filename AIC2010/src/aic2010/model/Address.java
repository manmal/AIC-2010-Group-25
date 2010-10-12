package aic2010.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)   //serialize all fields by default
@XmlRootElement(name="address")         //name root element
public class Address {

    //serialize this id as attribute
    @XmlAttribute(required=true)
    private String id;

    private String street;
    private String city;
    private int house;
    private int door;
    private String zipCode;

    private boolean shipping;
    private boolean billing;
    private boolean other;

    public boolean isBilling()
    {
        return billing;
    }

    public void setBilling(boolean billing)
    {
        this.billing = billing;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getDoor()
    {
        return door;
    }

    public void setDoor(int door)
    {
        this.door = door;
    }

    public int getHouse()
    {
        return house;
    }

    public void setHouse(int house)
    {
        this.house = house;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public boolean isOther()
    {
        return other;
    }

    public void setOther(boolean other)
    {
        this.other = other;
    }

    public boolean isShipping()
    {
        return shipping;
    }

    public void setShipping(boolean shipping)
    {
        this.shipping = shipping;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }
}
