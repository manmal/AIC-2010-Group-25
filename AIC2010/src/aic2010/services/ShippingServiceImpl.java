package aic2010.services;

import aic2010.datastore.MiniDB;
import aic2010.exception.UnknownAddressException;
import aic2010.exception.UnknownProductException;
import aic2010.model.Address;
import aic2010.model.Item;
import com.db4o.EmbeddedObjectContainer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.log4j.Logger;

@WebService(endpointInterface = "aic2010.services.ShippingService",
            serviceName = "ShippingService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping",
            portName="ShippingPT")
public class ShippingServiceImpl implements ShippingService {

    private static Logger log = Logger.getLogger(CustomerService.class);

    @Override
    public String shipItems(@WebParam(name="items")Item[] items, @WebParam(name="address")Address address)  throws UnknownAddressException, UnknownProductException {
        EmbeddedObjectContainer db = MiniDB.getDB();
        
        //check availability:
        if(db.queryByExample(address).size() == 0)
            throw new UnknownAddressException("", address.toString());
        for (Item item : items) {
            if(db.queryByExample(item.getProduct()).size() == 0)
                throw new UnknownProductException("", item.getProduct().getName());
        }

        // English locale is used to match the assignment:
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        log.info(sdf.format(new Date()));

        StringBuilder sb = new StringBuilder("Sending item");
        sb.append(items.length > 1 ? "s " : " ");
        for(int i=0; i<items.length; i++) {
            Item curItem = items[i];
            sb.append("'").append(curItem.getProduct().getName()).append("'");
            sb.append(items.length == i+1 ? " to" : ", ");
        }

        log.info(sb.toString());
        log.info(address.toString());
        
        return UUID.randomUUID().toString();
    }

}
