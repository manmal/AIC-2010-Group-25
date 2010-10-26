/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.model.Product;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */

@WebService
public interface WarehouseService extends SupplierService {
    public boolean check_availability(Product product, int amount);
}
