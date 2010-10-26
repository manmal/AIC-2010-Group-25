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

@WebService(endpointInterface = "aic2010.services.SupplierService",
            serviceName = "SupplierService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/supplier",
            portName="SupplierPT")
public class SupplierServiceImpl implements SupplierService {

    @Override
    public BigDecimal order(Product product, int amount) {
        BigDecimal overallAmount = product.getSingleUnitPrice().multiply(new BigDecimal(amount));

        return overallAmount;
    }

}
