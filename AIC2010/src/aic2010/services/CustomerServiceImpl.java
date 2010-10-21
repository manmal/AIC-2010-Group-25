package aic2010.services;

import aic2010.model.Customer;
import java.math.BigDecimal;
import javax.jws.WebService;
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

    private static Logger log = Logger.getLogger(CustomerService.class);

    @Override
    public Customer getCustomer()
    {
        log.debug("GetCustomer");
        return null;
    }

    @Override
    public void addCustomer(Customer customer)
    {
        log.debug("AddCustomer");
    }

    @Override
    public void updateCustomer(Customer customer)
    {
        log.debug("UpdateCustomer");
    }

    @Override
    public void deleteCustomer(Customer customer)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notify(Customer customer, String message)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateAccount(Customer customer, BigDecimal changedValue)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
