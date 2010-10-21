package aic2010.datastore;

import aic2010.model.Customer;
import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import java.io.File;
import java.util.List;

/**
 *
 * @author Alex
 */
public class MiniDB {
    private static String dbFile = "resources" + File.separator + "testdb";

    private EmbeddedObjectContainer db;

    private MiniDB() {
        clearDB();
        createDB();
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


    public static MiniDB mdb() {
        return MiniDBHolder.INSTANCE;
    }

    private static class MiniDBHolder {
        private static final MiniDB INSTANCE = new MiniDB();
    }
 }
