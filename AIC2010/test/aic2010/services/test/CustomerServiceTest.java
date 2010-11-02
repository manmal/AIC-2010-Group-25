package aic2010.services.test;

import java.util.ArrayList;
import aic2010.model.Address;
//import aic2010.datastore.MiniDB;
import java.util.List;
import aic2010.utils.Factory;
import at.ac.tuwien.infosys.aic10.ass1.dto.customerservice.CustomerServiceClient;
import aic2010.model.Customer;
import aic2010.services.CustomerService;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class CustomerServiceTest {

    private static CustomerService cs;

    public CustomerServiceTest() {
    }

    @BeforeClass
    public static void setUp() {
        CustomerServiceClient client = new CustomerServiceClient();
        cs = client.getCustomerPT();
    }

    /**
     * Populate the db with some customers and their addresses. Note that, for simplicity, orders will not be added.
     * 
     */
    @Before
    public void populateDB()
    {
        List<Address> list;

        Customer c1 = Factory.createCustomer("Customer1", BigDecimal.valueOf(1), null, null);
        Address a1 = Factory.createAddress("Vienna1", "1234", "Googlegasse", 20, 8, true, true, false);
        list = new ArrayList<Address>();
        list.add(a1);
        c1.setAddresses(list);
        cs.addCustomer(c1);

        Customer c2 = Factory.createCustomer("Customer2", BigDecimal.valueOf(17.3), null, null);
        Address a2 = Factory.createAddress("Vienna2", "1010", "Stephansdomplatzl", 1, 1, true, false, false);
        Address a3 = Factory.createAddress("Vienna3", "1230", "Karlsteich", 3, 0, false, true, false);
        list = new ArrayList<Address>();
        list.add(a2);
        list.add(a3);
        c2.setAddresses(list);
        cs.addCustomer(c2);

        Customer c3 = Factory.createCustomer("Customer3", BigDecimal.valueOf(-25.7), null, null);
        Address a4 = Factory.createAddress("Vienna4", "1110", "Googlegasse", 20, 8, false, false, true);
        list = new ArrayList<Address>();
        list.add(a4);
        c3.setAddresses(list);
        cs.addCustomer(c3);
    }

    @Test
    public void testGetCustomers()
    {
        List<Customer> customers = cs.getCustomers();
        assertEquals(3, customers.size());
    }

    /**
     * This test is similar to the one above except that the db will only contain one customer.
     * We have to test this since json seems to have problems with lists containing only one element.
     */
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
    }

    @Test
    public void testUpdateCustomer()
    {
        //pick second customer and remember id
        Customer customer = cs.getCustomers().get(1);
        String id = customer.getId();

        //check for the customers name and balance before update
        assertEquals("Customer2", customer.getName());
        assertEquals(BigDecimal.valueOf(17.3), customer.getOpenBalance());

        customer.setName("Customer2_renamed");
        customer.setOpenBalance(BigDecimal.valueOf(-23.8));
        cs.updateCustomer(customer);

        //check if customers are equal
        Customer updatedCustomer = cs.getCustomer(id);
        assertEquals(customer, updatedCustomer);
    }

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
    }

    @Test
    public void testGetCustomer()
    {
        for (Customer customer: cs.getCustomers())
        {
            Customer result = cs.getCustomer(customer.getId());
            assertEquals(customer, result);
        }
    }

    @Test
    public void testUpdateAccount()
    {
        //pick second customer and remember id
        Customer customer = cs.getCustomers().get(1);
        String id = customer.getId();

        //check for the customers name and balance before update
        assertEquals("Customer2", customer.getName());
        assertEquals(BigDecimal.valueOf(17.3), customer.getOpenBalance());

        cs.updateAccount(customer, BigDecimal.valueOf(13.5));

        //check if customers are equal
        Customer updatedCustomer = cs.getCustomer(id);
        assertEquals(BigDecimal.valueOf(30.8), updatedCustomer.getOpenBalance());
    }

    @Test
    public void testNotify()
    {
        cs.notify(cs.getCustomers().get(0), "Your credit card has been expired");
    }

}