/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.datastore.MiniDB;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.model.ProductEntry;
import aic2010.model.WarehouseAnswer;
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

@WebService(endpointInterface = "aic2010.services.WarehouseService",
            serviceName = "WarehouseService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse",
            portName="WarehousePT")
public class WarehouseServiceImpl extends SupplierServiceImpl implements WarehouseService{

    private Map<String, ProductEntry> warehouseProducts;
    private Logger log = Logger.getLogger(WarehouseServiceImpl.class);

    public WarehouseServiceImpl(){
            warehouseProducts = new HashMap<String, ProductEntry>();
//            actual_products = new HashMap<String, Product>();
            addProducts();
    }

    private void addProducts(){
        System.out.println("Called addProducts...");
        
        ProductEntry entry1 = new ProductEntry();
        entry1.setDeliveryTime(3);
        entry1.setAvailableAmount(3);

        ProductEntry entry2 = new ProductEntry();
        entry2.setDeliveryTime(3);
        entry2.setAvailableAmount(0);

        warehouseProducts.put("1", entry1);
        warehouseProducts.put("2", entry2);
    }

    @Override
    public WarehouseAnswer check_availability(Product product, int amount)
    throws UnknownProductException{
        log.info("starting check_availability Warehouse");
        if (product != null)
            log.info("Called check_availability with product id: " + product.getId());
        else
            log.info("Called check_availability with NULL product");
        if(warehouseProducts.containsKey(product.getId())){

            ProductEntry entry = warehouseProducts.get(product.getId());

            WarehouseAnswer answer = new WarehouseAnswer();
            answer.setDeliveryTime(entry.getDeliveryTime());

            log.info("available amount is " + entry.getAvailableAmount());
            if(amount<=entry.getAvailableAmount()){
                answer.setIsAvailable(true);
            }
            else{
                answer.setIsAvailable(false);
            }


            log.info("product with id " + product.getId() + " in amount " + amount + " is available: " + answer.isIsAvailable());
            return answer;
        }
        else{
            log.info("product with id " + product.getId() + " not found");
            throw new UnknownProductException("Could not find product", product.getName());
        }
    }

    @Override
    public BigDecimal order(Product product,
                        Integer amount)
    throws UnknownProductException{
        log.info("starting order product");
        EmbeddedObjectContainer db = MiniDB.getDB();
        Product actualProduct = null;

        if(warehouseProducts.containsKey(product.getId())){
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

        ProductEntry entry = warehouseProducts.get(product.getId());
        if(amount<=entry.getAvailableAmount()){
            entry.setAvailableAmount(entry.getAvailableAmount() - amount);
            warehouseProducts.put(product.getId(), entry);
        }
        else{
            log.info("warehouse doesn't contain " + amount + " products with id " + product.getId());
        }

        BigDecimal overallPrice = actualProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));
        
        log.info("product with id " + product.getId() + " in amount " + amount + " is available and costs " + overallPrice);
        return overallPrice;
    }
}
