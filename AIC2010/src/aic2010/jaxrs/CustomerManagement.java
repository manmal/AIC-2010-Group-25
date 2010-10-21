package aic2010.jaxrs;

import aic2010.datastore.MiniDB;
import aic2010.model.Customer;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.math.BigDecimal;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

@Path("/customerservice/")
@Produces("application/json")
@Consumes("application/json")
public class CustomerManagement {

    private static Logger log = Logger.getLogger(CustomerManagement.class);

    public CustomerManagement() {
    }

    @GET
    @Path("/customers/{id}/")
    public Customer getCustomer(@PathParam("id") String id)
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

            return customer;
        }
        else
        {
            log.warn("Warning, no customer with id=" + id + " found.");
        }

        return null;
    }


    @POST
    @Path("/customers/")
    public Response addCustomer(Customer customer)
    {
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

        customer.setId(UUID.randomUUID().toString());
        db.store(customer);

        log.info("Added Customer with id " + customer.getId() + " and name " + customer.getName());
        return Response.ok().build();
    }

    @PUT
    @Path("/customers/")
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

    @DELETE
    @Path("/customers/{id}/")
    public Response deleteCustomer(@PathParam("id") String id)
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

    @PUT
    @Path("/customers/update_account")
    public Response update_account(Customer customer, BigDecimal changedValue)
    {
        MiniDB mdb = MiniDB.mdb();
        EmbeddedObjectContainer db = mdb.getDB();

        //TODO_ check if this is a good idea to use methods registered as WebService
        Customer actCustomer = getCustomer(customer.getId());
        BigDecimal ob = actCustomer.getOpenBalance();
        ob.add(changedValue);

        updateCustomer(actCustomer);

        return Response.ok().build();
    }
}
