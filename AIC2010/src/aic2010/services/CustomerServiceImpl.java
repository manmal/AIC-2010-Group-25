package aic2010.services;

import aic2010.Main;
import aic2010.jaxrs.CustomerManagement;
import aic2010.model.Customer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author Alex
 */
@WebService(endpointInterface = "aic2010.services.CustomerService",
            serviceName = "CustomerService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/customer",
            portName="CustomerPT")
public class CustomerServiceImpl implements CustomerService {
   
    private CustomerManagement cm;
    private static Logger log = Logger.getLogger(CustomerService.class);

    public CustomerServiceImpl()
    {
        //TODO Test if this works on multiple clients       
        log.debug("Create JAX-RS CustomerManagement Proxy");
        cm = JAXRSClientFactory.create(Main.REST_BASE_URL, CustomerManagement.class);
    }

    @Override
    public Customer getCustomer(String id)
    {
        log.info("Forward get customer request to CustomerManagement service");
        return cm.getCustomer(id);
    }

    @Override
    public List<Customer> getCustomers()
    {
        log.info("Forward get all customers request to CustomerManagement service");
        //note that json returns an empty list as null
       return cm.getCustomers().getCustomers();
    }

    @Override
    public void addCustomer(Customer customer)
    {
        log.info("Forward add customer request to CustomerManagement service");
        cm.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer)
    {
        log.info("Forward update customer request to CustomerManagement service");
        cm.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(String id)
    {
        log.info("Forward delete customer request to CustomerManagement service");
        cm.deleteCustomer(id);
    }

    @Override
    public void notify(Customer customer, String message)
    {
        log.info("Forward notify request to CustomerManagement service");
        cm.notify(customer, message);
    }

    @Override
    public void updateAccount(Customer customer, BigDecimal changedValue)
    {
        log.info("Forward update account request to CustomerManagement service");
        cm.update_account(customer, changedValue);
    }
}
