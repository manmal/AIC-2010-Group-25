/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.model.Product;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */

@WebService
public interface WarehouseService extends SupplierService {

    @WebMethod(operationName="check_availability")
    public boolean check_availability(@WebParam(name="product") Product product,
                                      @WebParam(name="amount") int amount);
}
