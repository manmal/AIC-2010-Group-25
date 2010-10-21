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

}