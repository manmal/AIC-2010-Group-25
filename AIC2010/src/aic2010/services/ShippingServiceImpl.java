package aic2010.services;

import aic2010.model.Address;
import aic2010.model.Item;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "aic2010.services.ShippingService",
            serviceName = "ShippingService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping",
            portName="ShippingPT")
public class ShippingServiceImpl implements ShippingService {

    @Override
    public String shipItems(@WebParam(name="items")Item[] items, @WebParam(name="address")Address address) {

        // English locale is used to match the assignment:
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        System.out.println(sdf.format(new Date()));

        StringBuilder sb = new StringBuilder("Sending item");
        sb.append(items.length > 1 ? "s " : " ");
        for(int i=0; i<items.length; i++) {
            Item curItem = items[i];
            sb.append("'").append(curItem.getProduct().getName()).append("'");
            sb.append(items.length == i+1 ? " to" : ", ");
        }

        System.out.println(sb.toString());
        System.out.println(address.toString());
        
        return UUID.randomUUID().toString();
    }

}
