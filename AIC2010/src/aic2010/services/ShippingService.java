package aic2010.services;

import aic2010.model.Address;
import aic2010.model.Item;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Now it’s time to set up some JAX-WS Web services using Apache CXF, which use
 * the data model that we have developed earlier. First we start off slowly by
 * implementing our most simply Web service, the Shipping Service. This service
 * has only one Web service operation, ship_items, takes an array of items and
 * an address as input. Use the JAX-WS annotations (supported by Apache CXF) to
 * implement the service as a SOAP Web service (see a tutorial here8 to get you
 * started). Assign the target namespace
 * http: //infosys.tuwien.ac.at/aic10/ass1/dto/shipping, the port type name ShippingPT
 * and the service name ShippingService, and use explicit annotations to define all
 * relevant Web service details, such as operation name, parameter names, or
 * optionality. In the implementation of the services you can simply log a
 * message to System.out (see example below). Generate a random shipping
 * identifier (an java.util.UUID) and return this ID as simple String to the
 * service invoker. If the address or one of the products do not exist, you
 * should throw an UnknownAddressFault or UnknownProductFault SOAP fault.
 */
@WebService
public interface ShippingService {
    /**
     * Ships items and returns a unique id.
     * @param items
     * @param address
     * @return
     */
    @WebMethod(operationName="ship_items")
    public String shipItems(@WebParam(name="items")Item[] items, @WebParam(name="address")Address address);
}
