/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.model.WarehouseAnswer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebParam;
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

    private Map<String, WarehouseAnswer> products;

    public WarehouseServiceImpl(){
            products = new HashMap<String, WarehouseAnswer>();
            addProducts();
    }

    private void addProducts(){
        WarehouseAnswer answer = new WarehouseAnswer();
        answer.setIsAvailable(true);
        answer.setDeliveryTime(2);
//        products.put(, answer);
    }

    @Override
    public WarehouseAnswer check_availability(Product product, int amount) {
        return products.get(product.getId());
    }

    @Override
    public Double order(Integer amount) throws UnknownProductException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
