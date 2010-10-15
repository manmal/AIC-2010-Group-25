package aic2010.helloworld.db4o;

import com.db4o.ObjectContainer;
import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.cs.Db4oClientServer;
import java.io.File;

/**
 *
 * @author manuelmaly
 */
public class Db4oHelloWorldClient {

    public void deleteTestDB(String filename) {
        File file = new File(filename);
        if (file.exists())
            file.delete();
    }

    public EmbeddedObjectContainer createTestDB(String filename) {
        return Db4oEmbedded.openFile(filename);
    }

    public void closeTestDB(EmbeddedObjectContainer db) {
        db.close();
    }


}
