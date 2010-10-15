package aic2010.services;

import aic2010.model.Address;
import aic2010.model.Item;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.jws.WebService;

@WebService(endpointInterface = "aic2010.services.ShippingService",
            serviceName = "ShippingService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping",
            portName="ShippingPT")
public class ShippingServiceImpl implements ShippingService {

    @Override
    public String shipItems(Item[] items, Address address) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        System.out.println(sdf.format(new Date()));

        StringBuilder sb = new StringBuilder("Sending items ");
        for(int i=0; i<items.length; i++) {
            Item curItem = items[i];
            sb.append("'").append(curItem.getProduct().getName()).append("'");
            if (i+1 == items.length) {
                sb.append(" to");
            }
            else {
                sb.append(", ");
            }
        }

        // Maybe put this into Address.toString() instead,
        // and maybe refactor so its less ugly :P
        String addressString = address.getStreet() + " ";
        if(address.getHouse() > 0 && address.getDoor() > 0)
            addressString += address.getHouse() + "/" + address.getDoor() + ", ";
        else if (address.getHouse() > 0)
            addressString += address.getHouse() + ", ";
        else if (address.getDoor() > 0) {
            addressString += "Door " + address.getDoor() + ", ";
        }
        else
            addressString += ", ";

        addressString += address.getZipCode() + " ";
        addressString += address.getCity();

        System.out.println(addressString);
        
        return UUID.randomUUID().toString();
    }

}
