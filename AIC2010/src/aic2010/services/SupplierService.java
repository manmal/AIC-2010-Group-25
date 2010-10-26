/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.model.Product;
import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */
@WebService
public interface SupplierService {

    @WebMethod(operationName="order")
    public BigDecimal order(@WebParam(name="product") Product product,
                            @WebParam(name="amount") int amount);
}
