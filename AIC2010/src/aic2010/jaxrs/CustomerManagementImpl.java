package aic2010.jaxrs;

import aic2010.datastore.MiniDB;
import aic2010.model.Address;
import aic2010.model.Customer;
import aic2010.model.Customers;
import aic2010.model.Order;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.math.BigDecimal;
import java.util.UUID;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

public class CustomerManagementImpl implements CustomerManagement {

    private static Logger log = Logger.getLogger(CustomerManagementImpl.class);

    public CustomerManagementImpl() {
    }

    @Override
    public Customer getCustomer(String id)
    {
        EmbeddedObjectContainer db = MiniDB.getDB();

        Customer example = new Customer();
        example.setId(id);

        ObjectSet<Customer> resultSet = db.queryByExample(example);

        if (resultSet.hasNext())
        {
            Customer customer = resultSet.next();

            if (resultSet.hasNext())
            {
                log.warn("Warning, more than one customer with id=" + id + " found.");
            }

            log.debug("Returning customer with id=" + customer.getId());
            return customer;
        }
        else
        {
            log.warn("Warning, no customer with id=" + id + " found.");
        }

        return null;
    }

    @Override
    public Customers getCustomers()
    {
        EmbeddedObjectContainer db = MiniDB.getDB();

        ObjectSet<Customer> resultSet = db.query(Customer.class);
        log.info("Returning " + resultSet.size() + " customers");

        Customers customers = new Customers();
        customers.setCustomers(resultSet);

        return customers;
    }

    @Override
    public Response addCustomer(Customer customer)
    {
        EmbeddedObjectContainer db = MiniDB.getDB();

        Customer exists = getCustomer(customer.getId());

        if (exists == null)
        {
            db.store(customer);
            log.info("Added Customer with id " + customer.getId() + " and name " + customer.getName());
            return Response.ok().build();
        }
        
        log.info("Customer with id " + customer.getId() + " already exists. Did not store anything.");
        return Response.notModified().build();
    }

    @Override
    public Response updateCustomer(Customer customer)
    {
        EmbeddedObjectContainer db = MiniDB.getDB();

        Customer storedCustomer = getCustomer(customer.getId());
        if (storedCustomer != null)
        {
            storedCustomer.setAddresses(customer.getAddresses());
            storedCustomer.setName(customer.getName());
            storedCustomer.setOpenBalance(customer.getOpenBalance());
            storedCustomer.setOrders(customer.getOrders());
            db.store(storedCustomer);

            log.info("Updated Customer with id " + storedCustomer.getId() + " and name " + storedCustomer.getName());
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    @Override
    public Response deleteCustomer(String id)
    {
        EmbeddedObjectContainer db = MiniDB.getDB();

        Customer customer = getCustomer(id);

        if (customer != null)
        {
            db.delete(customer);
            log.info("Deleted Customer with id " + customer.getId() + " and name " + customer.getName());
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    @Override
    public Response notify(String customer, String message)
    {
        //nothing in the assignment says what to do with that...
        return Response.ok().build();
    }

    @Override
    public Response update_account(Customer customer, BigDecimal changedValue)
    {
        log.debug("Changed value is " + changedValue.toString());
        Customer actCustomer = getCustomer(customer.getId());
        BigDecimal ob = actCustomer.getOpenBalance();

        actCustomer.setOpenBalance(ob.add(changedValue));
        log.info("Changed the balance to " + ob.toString());
        updateCustomer(actCustomer);

        return Response.ok().build();
    }
}
