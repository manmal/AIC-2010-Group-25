package aic2010.jaxrs;

import aic2010.model.Customer;
import aic2010.model.Customers;
import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alex
 */
@Path("/customermanagement/")
@Produces("application/json")
@Consumes("application/json")
public interface CustomerManagement
{

    @GET
    @Path("/customers/{id}/")
    public Customer getCustomer(@PathParam("id") String id);

    @GET
    @Path("/customers/")
    public Customers getCustomers();

    @POST
    @Path("/customers/")
    public Response addCustomer(Customer customer);

    @PUT
    @Path("/customers/")
    public Response updateCustomer(Customer customer);

    @DELETE
    @Path("/customers/{id}/")
    public Response deleteCustomer(@PathParam("id") String id);

    @PUT
    @Path("/notify")
    public Response notify(@QueryParam("customer") String customer, @QueryParam("message") String message);

    @PUT
    @Path("/customers/update_account/")
    public Response update_account(Customer customer, @QueryParam("changedValue") BigDecimal changedValue);
}
