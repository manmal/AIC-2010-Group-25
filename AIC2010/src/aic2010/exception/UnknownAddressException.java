package aic2010.exception;

import aic2010.model.Address;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;

/**
 *
 * @author manuelmaly
 */
@WebFault(name="UnknownAddressFault", faultBean="UnknownAddressException")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnknownAddressException extends Exception {

    private String faultBean;

    public UnknownAddressException(String message, String faultBean, Throwable cause) {
        super(message, cause);
        this.faultBean = faultBean;
    }

    public UnknownAddressException(String message, String faultBean) {
        super(message);
        this.faultBean = faultBean;
    }

    public String getFaultInfo() {
        return "Unknown Address: " + faultBean;
    }
    
}
