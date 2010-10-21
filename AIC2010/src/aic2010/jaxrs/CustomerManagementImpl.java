package aic2010.jaxrs;

import aic2010.datastore.MiniDB;
import aic2010.model.Customer;
import aic2010.model.Customers;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.math.BigDecimal;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

public class CustomerManagementImpl implements CustomerManagement {

    //TODO Remove this
    private static long userID = 0;

    private static Logger log = Logger.getLogger(CustomerManagementImpl.class);

    public CustomerManagementImpl() {
    }

    @Override
    public Customer getCustomer(String id)
    {
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

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
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

        ObjectSet<Customer> resultSet = db.query(Customer.class);
        log.info("Returning " + resultSet.size() + " customers");

        Customers customers = new Customers();
        customers.setCustomers(resultSet);

        return customers;
    }

    @Override
    public Response addCustomer(Customer customer)
    {
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

        //customer.setId(UUID.randomUUID().toString());
        customer.setId(Long.toString(++userID));
        db.store(customer);

        log.info("Added Customer with id " + customer.getId() + " and name " + customer.getName());
        return Response.ok().build();
    }

    @Override
    public Response updateCustomer(Customer customer)
    {
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

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
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

        Customer customer = getCustomer(id);

        if (customer != null)
        {
            db.delete(customer);
            log.info("Deleted Customer with id " + customer.getId() + " and name " + customer.getName());
            return Response.ok().build();
        }

        return Response.notModified().build();
    }
   
    /*
    public Response notify(Customer customer, String message)
    {
        //nothing in the assignment says what to do with that...
        return Response.ok().build();
    }*/

    @Override
    public Response update_account(Customer customer, BigDecimal changedValue)
    {
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

        //TODO_ check if this is a good idea to use methods registered as WebService
        Customer actCustomer = getCustomer(customer.getId());
        BigDecimal ob = actCustomer.getOpenBalance();
        ob.add(changedValue);
        log.info("Changed the balance to " + ob.toString());

        updateCustomer(actCustomer);

        return Response.ok().build();
    }
}
