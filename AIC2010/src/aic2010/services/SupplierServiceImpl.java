/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.datastore.MiniDB;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import org.apache.log4j.Logger;

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
    private Logger log = Logger.getLogger(SupplierServiceImpl.class);


    public SupplierServiceImpl(){
        products = new HashMap<String, Integer>();
        addProducts();
    }

    @Override
    public BigDecimal order(Product product,
                        Integer amount)
    throws UnknownProductException{
        log.info("starting order product Supplier");
        EmbeddedObjectContainer db = MiniDB.getDB();
        Product actualProduct = null;

        if(products.containsKey(product.getId())){
           ObjectSet<Product> result = db.queryByExample(product);
           if(result.hasNext()){
               actualProduct = result.next();
           }
           else{
               log.info("product with id " + product.getId() + " not found");
               throw new UnknownProductException("Could not find product", product.getId());
           }
        }
        else{
            log.info("product with id " + product.getId() + " not found");
            throw new UnknownProductException("Could not find product", product.getId());
        }

        BigDecimal overallAmount = actualProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));

        log.info("product with id " + product.getId() + " in amount " + amount + " is available and costs " + overallAmount);
        return overallAmount;
    }

    private void addProducts(){
        products.put("1", 23);
        products.put("2", 33);
    }

}
