package aic2010.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)   //serialize all fields by default
@XmlRootElement(name="item")            //name root element
public class Item {

    private int quantity;

    private Product product;
    private Order order;

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass()))
            return false;
        Item otherItem = (Item)obj;
        return (otherItem.getOrder().equals(this.getOrder()) &&
                otherItem.getProduct().equals(this.getProduct()) &&
                otherItem.getQuantity() == this.getQuantity());
    }

    /**
     * We have no id available, so lets make a composite hashcode out of
     * the order and the product, which are not supposed to change.
     * @return
     */
    @Override
    public int hashCode() {
        return getOrder().hashCode() + getProduct().hashCode();
    }
}
