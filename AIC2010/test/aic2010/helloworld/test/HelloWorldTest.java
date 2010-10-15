/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.helloworld.test;

import aic2010.helloworld.UserImpl;
import aic2010.helloworld.db4o.Db4oHelloWorldClient;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author manuelmaly
 */
public class HelloWorldTest {

    @Test
    public void testDb4oTestClient() {
        String dbFile = "resources/testdb";
        Db4oHelloWorldClient client = new Db4oHelloWorldClient();
        client.deleteTestDB(dbFile);
        EmbeddedObjectContainer db = client.createTestDB(dbFile);

        UserImpl user = new UserImpl("testname");
        db.store(user);
        client.closeTestDB(db);

        File dbFile2 = new File(dbFile);
        Assert.assertEquals(true, dbFile2.exists());

        EmbeddedObjectContainer db2 = client.createTestDB(dbFile);
        ObjectSet<Object> usersByCertainExample = db2.queryByExample(user);
        ObjectSet<Object> usersByClass = db2.queryByExample(UserImpl.class);
        
        Assert.assertEquals(usersByCertainExample.size(), usersByClass.size());
        Assert.assertEquals(user, usersByCertainExample.get(0));
    }
}
