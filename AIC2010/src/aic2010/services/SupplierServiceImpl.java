/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.TestDataManager;
import aic2010.datastore.MiniDB;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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

    private Map<String, Integer> products;


    public SupplierServiceImpl(){
        products = new HashMap<String, Integer>();
        addProducts();
    }

    @Override
    public BigDecimal order(Product product,
                        Integer amount)
    throws UnknownProductException{
        EmbeddedObjectContainer db = MiniDB.getDB();
        Product actualProduct = null;

        if(products.containsKey(product.getId())){
           ObjectSet<Product> result = db.queryByExample(product);
           if(result.hasNext()){
               actualProduct = result.next();
           }
           else{
               throw new UnknownProductException("Could not find product", product.getId());
           }
        }
        else{
            throw new UnknownProductException("Could not find product", product.getId());
        }

        BigDecimal overallAmount = actualProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));
        return overallAmount;
    }

    private void addProducts(){
        products.put("1", 23);
        products.put("2", 33);
    }

}
