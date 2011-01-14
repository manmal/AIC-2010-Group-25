
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
 *         &lt;element name="shipping_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "shippingId"
})
@XmlRootElement(name = "parameters_callback")
public class ParametersCallback {

    @XmlElement(name = "shipping_id", required = true)
    protected String shippingId;

    /**
     * Gets the value of the shippingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingId() {
        return shippingId;
    }

    /**
     * Sets the value of the shippingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingId(String value) {
        this.shippingId = value;
    }

}
