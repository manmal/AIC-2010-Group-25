package aic2010.services.test;

import at.ac.tuwien.infosys.aic10.ass1.dto.customerservice.CustomerServiceClient;
import aic2010.services.CustomerServiceImpl;
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

        try {
            assertNull(cs.getCustomer());
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    /**
     * Test of addCustomer method, of class CustomerService.
     */
    @Test
    public void testAddCustomer()
    {
        System.out.println("addCustomer");
        Customer customer = null;
        CustomerService instance = new CustomerServiceImpl();
        instance.addCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCustomer method, of class CustomerService.
     */
    @Test
    public void testUpdateCustomer()
    {
        System.out.println("updateCustomer");
        Customer customer = null;
        CustomerService instance = new CustomerServiceImpl();
        instance.updateCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class CustomerService.
     */
    @Test
    public void testDeleteCustomer()
    {
        System.out.println("deleteCustomer");
        Customer customer = null;
        CustomerService instance = new CustomerServiceImpl();
        instance.deleteCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notify method, of class CustomerService.
     */
    @Test
    public void testNotify()
    {
        System.out.println("notify");
        Customer customer = null;
        String message = "";
        CustomerService instance = new CustomerServiceImpl();
        instance.notify(customer, message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAccount method, of class CustomerService.
     */
    @Test
    public void testUpdateAccount()
    {
        System.out.println("updateAccount");
        Customer customer = null;
        BigDecimal changedValue = null;
        CustomerService instance = new CustomerServiceImpl();
        instance.updateAccount(customer, changedValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}