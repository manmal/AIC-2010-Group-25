/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rudolf
 */
@XmlRootElement(name="WarehouseAnswer")
public class WarehouseAnswer {

    private boolean isAvailable;

    private int deliveryTime;

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WarehouseAnswer other = (WarehouseAnswer) obj;
        if (this.isAvailable != other.isAvailable) {
            return false;
        }
        if (this.deliveryTime != other.deliveryTime) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.deliveryTime;
        return hash;
    }
}
