/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.services.test;

import aic2010.TestDataManager;
import aic2010.exception.UnknownProductException;
import aic2010.model.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rudolf
 */
public class SupplierRegistryTest {

    public SupplierRegistryTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testFindProductSupplier1(){
        Product product = TestDataManager.getProduct(false, true);
    }

    @Test
    public void testFindProductSupplier2(){
        Product product = TestDataManager.getProduct2();
    }

    @Test(expected=UnknownProductException.class)
    public void testFindMissingProduct(){
        Product missingProduct = TestDataManager.getProduct(true, true);

        
    }

}