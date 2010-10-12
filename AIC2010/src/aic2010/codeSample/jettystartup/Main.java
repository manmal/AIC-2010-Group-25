/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.codeSample.jettystartup;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;

/**
 * For test use only!
 * This starts an embedded jetty instance at port 9090, providing a dummy customerService.
 * @author manuelmaly
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
         
            Endpoint.publish("http://localhost:9090/customerService", new CustomerServiceImpl());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
