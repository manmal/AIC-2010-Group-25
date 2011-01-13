/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.TestDataManager;
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

/**
 *
 * @author rudolf
 */

@WebService(endpointInterface = "aic2010.services.WarehouseService",
            serviceName = "WarehouseService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse",
            portName="WarehousePT")
public class WarehouseServiceImpl extends SupplierServiceImpl implements WarehouseService{

    private Map<String, ProductEntry> products;

    public WarehouseServiceImpl(){
            products = new HashMap<String, ProductEntry>();
//            actual_products = new HashMap<String, Product>();
            addProducts();
    }

    private void addProducts(){
        System.out.println("Called addProducts...");
        
        ProductEntry entry1 = new ProductEntry();
        entry1.setDeliveryTime(3);
        entry1.setAvailableAmount(3);

        ProductEntry entry2 = new ProductEntry();
        entry1.setDeliveryTime(3);
        entry1.setAvailableAmount(0);

        products.put("1", entry1);
        products.put("2", entry2);
    }

    @Override
    public WarehouseAnswer check_availability(Product product, int amount)
    throws UnknownProductException{
        if (product != null)
            System.out.println("Called check_availability with product id: " + product.getId());
        else
            System.out.println("Called check_availability with NULL product");
        if(products.containsKey(product.getId())){

            ProductEntry entry = products.get(product.getId());

            WarehouseAnswer answer = new WarehouseAnswer();
            answer.setDeliveryTime(entry.getDeliveryTime());

            if(amount<=entry.getAvailableAmount())
                answer.setIsAvailable(true);
            else
                answer.setIsAvailable(false);

            return answer;
        }
        else{
            throw new UnknownProductException("Could not find product", product.getName());
        }
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
}
