/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aic2010.codeSample.jettystartup;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 * Debug use only!
 * @author manuelmaly
 */
@WebService
public interface CustomerService {
	@WebMethod(action = "getCustomer")
	public Customer getCustomer(
			@WebParam(name = "customerNummber") String customerNumber)
		throws Exception;
}
