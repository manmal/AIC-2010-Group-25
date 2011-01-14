
package at.ac.tuwien.infosys.aic10.ass2.orderprocessschema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the at.ac.tuwien.infosys.aic10.ass2.orderprocessschema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: at.ac.tuwien.infosys.aic10.ass2.orderprocessschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Parameters }
     * 
     */
    public Parameters createParameters() {
        return new Parameters();
    }

    /**
     * Create an instance of {@link OrderResult }
     * 
     */
    public OrderResult createOrderResult() {
        return new OrderResult();
    }

    /**
     * Create an instance of {@link CallbackResult }
     * 
     */
    public CallbackResult createCallbackResult() {
        return new CallbackResult();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link ParametersCallback }
     * 
     */
    public ParametersCallback createParametersCallback() {
        return new ParametersCallback();
    }

    /**
     * Create an instance of {@link OrderPart }
     * 
     */
    public OrderPart createOrderPart() {
        return new OrderPart();
    }

}
