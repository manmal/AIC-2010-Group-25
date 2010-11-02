/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.registry.EndpointReferenceType;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */
@WebService
public interface ServiceRegistry {

    @WebMethod(operationName="find_supplier")
    public EndpointReferenceType findSupplier(Product product)
            throws UnknownProductException;
}
