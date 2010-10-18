package aic2010.datastore;

import aic2010.model.Customer;
import aic2010.utils.Log;
import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Alex
 */
public class MiniDB {
    private static String dbFile = "resources" + File.pathSeparator + "testdb";

    private EmbeddedObjectContainer db;

    private MiniDB() {
        clearDB();
        createDB();
    }

    public void createCustomer(Customer customer)
    {
        customer.setId(UUID.randomUUID().toString());
        db.store(customer);

        Log.println("Added Customer with id " + customer.getId() + " and name " + customer.getName());
    }

    public boolean updateCustomer(Customer customerData)
    {
        Customer customer = getCustomer(customerData.getId());
        if (customer != null)
        {
            customer.setAddresses(customerData.getAddresses());
            customer.setName(customerData.getName());
            customer.setOpenBalance(customerData.getOpenBalance());
            customer.setOrders(customerData.getOrders());

            db.store(customer);

            Log.println("Updated Customer with id " + customer.getId() + " and name " + customer.getName());
            return true;
        }

        return false;
    }

    public boolean deleteCustomer(String id)
    {
        Customer customer = getCustomer(id);

        if (customer != null)
        {
            db.delete(customer);
            Log.println("Deleted Customer with id " + customer.getId() + " and name " + customer.getName());
            return true;
        }

        return false;
    }

    public Customer getCustomer(String id)
    {
        Customer example = new Customer();
        example.setId(id);

        ObjectSet<Customer> resultSet = db.queryByExample(example);

        if (resultSet.hasNext())
        {
            Customer customer = resultSet.next();

            if (resultSet.hasNext())
            {
                Log.println("Warning, more than one customer with id=" + id + " found.");
            }

            return customer;
        }
        else
        {
            Log.println("Warning, no customer with id=" + id + " found.");
        }

        return null;
    }

    public List<Customer> getCustomers()
    {
        return db.query(Customer.class);
    }

    public EmbeddedObjectContainer getDB()
    {
        return db;
    }

    public void closeTestDB(EmbeddedObjectContainer db) {
        db.close();
    }

    private void clearDB()
    {
        File file = new File(dbFile);
        if (file.exists())
            file.delete();
    }

    private void createDB() {
        db = Db4oEmbedded.openFile(dbFile);
    }

    public void resetRunningDB() {
        db.close();
        clearDB();
        createDB();
    }


    public static MiniDB getInstance() {
        return MiniDBHolder.INSTANCE;
    }

    private static class MiniDBHolder {
        private static final MiniDB INSTANCE = new MiniDB();
    }
 }
