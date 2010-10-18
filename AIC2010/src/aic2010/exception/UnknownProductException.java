package aic2010.exception;

import aic2010.model.Address;
import aic2010.model.Product;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;

/**
 *
 * @author manuelmaly
 */
@WebFault(name="UnknownProductFault", faultBean="UnknownProductException")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnknownProductException extends Exception {

    private String faultBean;

    public UnknownProductException(String message, String faultBean, Throwable cause) {
        super(message, cause);
        this.faultBean = faultBean;
    }

    public UnknownProductException(String message, String faultBean) {
        super(message);
        this.faultBean = faultBean;
    }
    
    public String getFaultInfo() {
        return "Unknown Product: " + faultBean;
    }

    
}
