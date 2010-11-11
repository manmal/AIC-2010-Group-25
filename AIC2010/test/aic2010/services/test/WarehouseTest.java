/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.services.WarehouseService;
import at.ac.tuwien.infosys.aic10.ass1.dto.warehouse.WarehouseServiceClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rudolf
 */
public class WarehouseTest {

    private static WarehouseServiceClient client;
    private static WarehouseService warehouse;


    @Before
    public void setUp() {
        client = new WarehouseServiceClient();
        warehouse = client.getWarehousePT();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void foo(){
        
    }

}