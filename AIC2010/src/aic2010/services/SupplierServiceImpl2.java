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
@WebService(endpointInterface = "aic2010.services.supplier1",
            serviceName = "SupplierService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/supplier",
            portName="SupplierPT1")
public class SupplierServiceImpl2 implements SupplierService {

    @Override
    public BigDecimal order(Product product, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
