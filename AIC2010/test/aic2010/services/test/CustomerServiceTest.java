package aic2010.services.test;

import aic2010.datastore.MiniDB;
import java.util.List;
import aic2010.utils.Factory;
import at.ac.tuwien.infosys.aic10.ass1.dto.customerservice.CustomerServiceClient;
import aic2010.Main;
import aic2010.model.Customer;
import aic2010.services.CustomerService;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class CustomerServiceTest {

    public CustomerServiceTest() {
    }

    @Before
    public void setUp() {
        MiniDB.mdb().resetRunningDB();
        Main.startCustomerManagementService();
    }

    @After
    public void tearDown() {
        Main.stopAllServices();
    }

    /**
     * Test of getCustomer method, of class CustomerService.
     */
    @Test
    public void testGetCustomer()
    {
        CustomerServiceClient client = new CustomerServiceClient();
        CustomerService cs = client.getCustomerPT();

        Customer customer = Factory.createCustomer("Customer1", BigDecimal.valueOf(17.3), null, null);
        cs.addCustomer(customer);

        Customer newCustomer = cs.getCustomer("1");

        assertEquals("Customer1", newCustomer.getName());
        assertEquals(BigDecimal.valueOf(17.3), newCustomer.getOpenBalance());
    }

    @Test
    public void testGetCustomersWithOneCustomer()
    {
        //We have to test this since json seems to have problems with lists containing only one element
        CustomerServiceClient client = new CustomerServiceClient();
        CustomerService cs = client.getCustomerPT();

        Customer c1 = Factory.createCustomer("Customer1", BigDecimal.valueOf(1), null, null);
        cs.addCustomer(c1);

        List<Customer> customers = cs.getCustomers();
        c1 = customers.get(0);
        assertEquals(1, customers.size());
        assertEquals("Customer1", c1.getName());
        assertEquals(BigDecimal.valueOf(1), c1.getOpenBalance());
    }


    @Test
    public void testGetCustomers()
    {
        CustomerServiceClient client = new CustomerServiceClient();
        CustomerService cs = client.getCustomerPT();

        Customer c1 = Factory.createCustomer("Customer1", BigDecimal.valueOf(1), null, null);
        Customer c2 = Factory.createCustomer("Customer2", BigDecimal.valueOf(17.3), null, null);
        Customer c3 = Factory.createCustomer("Customer3", BigDecimal.valueOf(-25.7), null, null);
        cs.addCustomer(c1);
        cs.addCustomer(c2);
        cs.addCustomer(c3);

        List<Customer> customers = cs.getCustomers();
        assertEquals(3, customers.size());
    }

}