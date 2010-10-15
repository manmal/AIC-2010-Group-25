/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.model.Product;
import java.math.BigDecimal;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */
@WebService
public interface SupplierService {
    public BigDecimal order(Product product, int amount);
}
