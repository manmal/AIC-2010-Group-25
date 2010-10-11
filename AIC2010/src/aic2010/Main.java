/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import org.mortbay.jetty.Server;

/**
 *
 * @author manuelmaly
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Server server = new Server(8088);
        try {
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
