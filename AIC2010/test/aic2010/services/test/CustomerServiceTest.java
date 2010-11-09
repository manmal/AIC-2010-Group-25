package aic2010.services.test;

//import aic2010.datastore.MiniDB;
import aic2010.model.Address;
import org.apache.log4j.Logger;
//import aic2010.datastore.MiniDB;
import java.util.List;
import at.ac.tuwien.infosys.aic10.ass1.dto.customerservice.CustomerServiceClient;
import aic2010.model.Customer;
import aic2010.services.CustomerService;
import java.math.BigDecimal;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class CustomerServiceTest
{
    private static Logger log = Logger.getLogger(CustomerServiceTest.class);
    private static CustomerService cs;

    public CustomerServiceTest()
    {
    }

    @BeforeClass
    public static void setUp()
    {
        CustomerServiceClient client = new CustomerServiceClient();
        cs = client.getCustomerPT();
    }

    @Test
    public void testGetCustomers()
    {
        log.info("Testing getCustomers");
        List<Customer> customers = cs.getCustomers();

        //NOTE Change this, when testdata changes...
        assertEquals(5, customers.size());

        for (Customer customer: customers)
        {
            log.info("Received Customer: " + customer.getName());
            log.info("\twith id: " + customer.getId());
            if (customer.getAddresses() != null)
            {
                for (Address a: customer.getAddresses())
                {
                    log.info("\thas address: " + a.getStreet() + " " + a.getHouse() + "/" + a.getDoor() + " in " + a.getCity());
                }
            }
            else
            {
                log.info("\thas no address");
            }
        }
    }

    /**
     * This test is similar to the one above except that the db will only contain one customer.
     * We have to test this since json seems to have problems with lists containing only one element.
     */
    /*
    @Test
    public void testGetCustomersWithOneCustomer()
    {
    Customer c1 = Factory.createCustomer("Customer1", BigDecimal.valueOf(1), null, null);
    cs.addCustomer(c1);

    List<Customer> customers = cs.getCustomers();
    Customer c2 = customers.get(0);

    //here equals cannot be used since the local object has not yet an id
    assertEquals(c1.getName(), c2.getName());
    assertEquals(c1.getOpenBalance(), c2.getOpenBalance());
    assertNull(c2.getAddresses());
    assertNull(c2.getOrders());
    } */
    @Test
    public void testUpdateCustomer()
    {
        log.info("Test updateCustomer");
        //pick second customer and remember id
        Customer customer = cs.getCustomers().get(1);
        String id = customer.getId();

        log.info("Select customer " + customer.getName());
        log.info("Open balance is " + customer.getOpenBalance());

        String newName = "Fritz J.";
        BigDecimal newBalance = BigDecimal.valueOf(-23.8);
        log.info("Rename customer to " + newName);
        customer.setName(newName);
        log.info("Set open balance to " + newBalance);
        customer.setOpenBalance(newBalance);

        log.info("Update customer");
        cs.updateCustomer(customer);

        log.info("Request customer again by id");
        Customer updatedCustomer = cs.getCustomer(id);
        log.info("Select customer " + updatedCustomer.getName());
        log.info("Open balance is " + updatedCustomer.getOpenBalance());

        assertEquals(customer, updatedCustomer);
    }

    /*
    @Test
    public void testDeleteCustomer()
    {
    List<Customer> original = cs.getCustomers();
    List<Customer> persisted;
    String id;

    //delete second element from list
    id = original.get(1).getId();
    cs.deleteCustomer(id);
    persisted = cs.getCustomers();

    original.remove(1);

    assertEquals(original, persisted);
    assertEquals(2, persisted.size());

    //delete first element in list
    id = original.get(0).getId();
    cs.deleteCustomer(id);
    persisted = cs.getCustomers();

    original.remove(0);

    assertEquals(original, persisted);
    assertEquals(1, persisted.size());

    //delete last element in list (list is empty afterwards)
    id = original.get(0).getId();
    cs.deleteCustomer(id);
    persisted = cs.getCustomers();

    original.remove(0);

    //note that json returns an empty list as null
    assertNull(persisted);
    }*/
    @Test
    public void testGetCustomer()
    {
        log.info("Test getCustomer");

        log.info("Get each customer");
        for (Customer customer: cs.getCustomers())
        {
            if (customer.getId() != null)
            {
                Customer result = cs.getCustomer(customer.getId());
                log.info("Retrieved customer " + customer.getName());
                log.info("\twith balance " + customer.getOpenBalance());
                if (customer.getAddresses() != null)
                {
                    for (Address a: customer.getAddresses())
                    {
                        log.info("\thas address: " + a.getStreet() + " " + a.getHouse() + "/" + a.getDoor() + " in " + a.getCity());
                    }
                }
                else
                {
                    log.info("\thas no address");
                }

                assertEquals(customer, result);
            }
            else
            {
                log.info("Customer " + customer.getName() + " with no id is not queried");
            }
        }
    }

    @Test
    public void testUpdateAccount()
    {
        log.info("Test updateAccount");
        Customer customer = cs.getCustomers().get(1);
        String id = customer.getId();

        log.info("Select customer " + customer.getName());
        log.info("Open balance is " + customer.getOpenBalance());

        BigDecimal oldBalance = customer.getOpenBalance();
        BigDecimal addBalance = BigDecimal.valueOf(13.5);

        log.info("Update account with balance " + addBalance);
        cs.updateAccount(customer, addBalance);

        log.info("Request customer again by id");
        Customer updatedCustomer = cs.getCustomer(id);
        log.info("Select customer " + customer.getName());
        log.info("Open balance is " + customer.getOpenBalance());

        assertEquals(oldBalance.add(addBalance), updatedCustomer.getOpenBalance());
    }

    @Test
    public void testNotify()
    {
        log.info("Test notify");
        Customer customer = cs.getCustomers().get(0);
        cs.notify(customer, "Your credit card has been expired");
        log.info("Customer " + customer.getName() + " should have been notified that his credit card has been expired");
    }
}
