/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.model;

import java.io.Serializable;

/**
 *
 * @author rudolf
 */
public class ProductEntry implements Serializable{
    
    private static final long serialVersionUID = 1L;
    

    private int availableAmount;

    private int deliveryTime;

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductEntry other = (ProductEntry) obj;
        if (this.availableAmount != other.availableAmount) {
            return false;
        }
        if (this.deliveryTime != other.deliveryTime) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.availableAmount;
        hash = 59 * hash + this.deliveryTime;
        return hash;
    }
}
