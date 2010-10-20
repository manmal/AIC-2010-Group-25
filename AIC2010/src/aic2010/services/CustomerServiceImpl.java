package aic2010.services;

import aic2010.model.Customer;
import aic2010.utils.Log;
import java.math.BigDecimal;
import javax.jws.WebService;

/**
 *
 * @author Alex
 */
@WebService(endpointInterface = "aic2010.services.CustomerService",
            serviceName = "CustomerService",
            targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/customer",
            portName="CustomerPT")
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer()
    {
        Log.println("GetCustomer");
        return null;
    }

    @Override
    public void addCustomer(Customer customer)
    {
        Log.println("AddCustomer");
    }

    @Override
    public void updateCustomer(Customer customer)
    {
        Log.println("UpdateCustomer");
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
