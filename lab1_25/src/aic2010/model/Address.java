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

    /**
     * Special format for the ShippingService - correct reference in ShippingServiceImpl
     * if you extract this to a dedicated method!
     */
    @Override
    public String toString() {
        String addressString = getStreet() + " ";
        if(getHouse() > 0 && getDoor() > 0)
            addressString += getHouse() + "/" + getDoor() + ", ";
        else if (getHouse() > 0)
            addressString += getHouse() + ", ";
        else if (getDoor() > 0) {
            addressString += "Door " + getDoor() + ", ";
        }
        else
            addressString += ", ";

        addressString += getZipCode() + " ";
        addressString += getCity();
        return addressString;
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
        final Address other = (Address) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id))
        {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street))
        {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city))
        {
            return false;
        }
        if (this.house != other.house)
        {
            return false;
        }
        if (this.door != other.door)
        {
            return false;
        }
        if ((this.zipCode == null) ? (other.zipCode != null) : !this.zipCode.equals(other.zipCode))
        {
            return false;
        }
        if (this.shipping != other.shipping)
        {
            return false;
        }
        if (this.billing != other.billing)
        {
            return false;
        }
        if (this.other != other.other)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 67 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 67 * hash + this.house;
        hash = 67 * hash + this.door;
        hash = 67 * hash + (this.zipCode != null ? this.zipCode.hashCode() : 0);
        hash = 67 * hash + (this.shipping ? 1 : 0);
        hash = 67 * hash + (this.billing ? 1 : 0);
        hash = 67 * hash + (this.other ? 1 : 0);
        return hash;
    }



}
