/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebParam;
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

    private Map<Product, Integer> products;


    public SupplierServiceImpl(){
        products = new HashMap<Product, Integer>();
        addProducts();
    }

    @Override
    public Double order(
                            @WebParam(name="amount")Integer amount)
    throws UnknownProductException{
//        if(products.containsKey(product)){
//            BigDecimal overallAmount = product.getSingleUnitPrice().multiply(new BigDecimal(amount));
//            return overallAmount.doubleValue();
//        }
//        else{
//            throw new UnknownProductException("Could not find product", product.getName());
//        }
        return new Double(0);
    }

    private void addProducts(){
        Product product1 = TestDataManager.getProduct(false, true);
        Product product2 = TestDataManager.getProduct2();

        products.put(product1, 23);
        products.put(product2, 33);
    }

}
