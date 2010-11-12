/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services;

import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import aic2010.model.ProductEntry;
import aic2010.model.WarehouseAnswer;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author rudolf
 */

@WebService(endpointInterface = "aic2010.services.WarehouseService",
            serviceName = "WarehouseService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse",
            portName="WarehousePT")
public class WarehouseServiceImpl extends SupplierServiceImpl implements WarehouseService{

    private Map<Product, ProductEntry> products;

    public WarehouseServiceImpl(){
            products = new HashMap<Product, ProductEntry>();
            addProducts();
    }

    private void addProducts(){
        Product product1 = TestDataManager.getProduct(false, true);
        
        ProductEntry entry1 = new ProductEntry();
        entry1.setDeliveryTime(3);
        entry1.setAvailableAmount(3);

        Product product2 = TestDataManager.getProduct2();
        ProductEntry entry2 = new ProductEntry();
        entry2.setDeliveryTime(2);
        entry2.setAvailableAmount(1);

        products.put(product1, entry1);
        products.put(product2, entry2);
    }

    @Override
    public WarehouseAnswer check_availability(Product product, int amount)
    throws UnknownProductException{
        if(products.containsKey(product)){

            ProductEntry entry = products.get(product);
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
}
