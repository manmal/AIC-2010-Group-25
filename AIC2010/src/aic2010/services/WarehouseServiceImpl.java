/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.model.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;

/**
 *
 * @author rudolf
 */

@WebService(endpointInterface = "aic2010.services.WarehouseService",
            serviceName = "WarehouseService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse",
            portName="WarehousePT")
public class WarehouseServiceImpl implements WarehouseService {

    private Map<String, Product> products;

    public WarehouseServiceImpl(){
            products = new HashMap<String, Product>();
    }

    @Override
    public boolean check_availability(Product product, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BigDecimal order(Product product, int amount) {
        BigDecimal overallAmount = product.getSingleUnitPrice().multiply(new BigDecimal(amount));

        return overallAmount;
    }

}
