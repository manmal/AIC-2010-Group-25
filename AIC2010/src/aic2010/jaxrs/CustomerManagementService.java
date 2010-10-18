package aic2010.jaxrs;

import aic2010.datastore.MiniDB;
import aic2010.model.Customer;
import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/customerservice/")
@Produces("application/json")
@Consumes("application/json")
public class CustomerManagementService {
  
    public CustomerManagementService() {      
    }

    @GET
    @Path("/customers/{id}/")
    public Customer getCustomer(@PathParam("id") String id)
    {
        MiniDB db = MiniDB.mdb();
        return db.getCustomer(id);
    }

    @POST
    @Path("/customers/")
    public Response addCustomer(Customer customer)
    {
        MiniDB db = MiniDB.mdb();
        db.createCustomer(customer);
        return Response.ok().build();
    }

    @PUT
    @Path("/customers/")
    public Response updateCustomer(Customer customer)
    {
        MiniDB db = MiniDB.mdb();
        boolean updated = db.updateCustomer(customer);

        if (updated)
        {
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    @DELETE
    @Path("/customers/{id}/")
    public Response deleteCustomer(@PathParam("id") String id)
    {
        MiniDB db = MiniDB.mdb();
        boolean deleted = db.deleteCustomer(id);

        if (deleted)
        {
            return Response.ok().build();
        }

        return Response.notModified().build();
    }
   
    public Response notify(Customer customer, String message)
    {
        //nothing in the assignment says what to do with that...
        return Response.ok().build();
    }

    @PUT
    @Path("/customers/update_account")
    public Response update_account(Customer customer, BigDecimal changedValue)
    {
        MiniDB db = MiniDB.mdb();
        Customer actCustomer = db.getCustomer(customer.getId());
        BigDecimal ob = actCustomer.getOpenBalance();
        ob.add(changedValue);

        db.updateCustomer(actCustomer);

        return Response.ok().build();
    }
}
