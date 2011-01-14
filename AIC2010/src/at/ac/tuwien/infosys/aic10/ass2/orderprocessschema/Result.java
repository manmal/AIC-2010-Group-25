
package at.ac.tuwien.infosys.aic10.ass2.orderprocessschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderResult" type="{http://infosys.tuwien.ac.at/aic10/ass2/OrderProcessSchema}orderResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderResult"
})
@XmlRootElement(name = "result")
public class Result {

    @XmlElement(required = true)
    protected OrderResult orderResult;

    /**
     * Gets the value of the orderResult property.
     * 
     * @return
     *     possible object is
     *     {@link OrderResult }
     *     
     */
    public OrderResult getOrderResult() {
        return orderResult;
    }

    /**
     * Sets the value of the orderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderResult }
     *     
     */
    public void setOrderResult(OrderResult value) {
        this.orderResult = value;
    }

}
