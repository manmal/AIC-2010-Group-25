package aic2010;

import at.ac.tuwien.infosys.aic10.ass2.orderprocessschema.ParametersCallback;
import at.ac.tuwien.infosys.aic10.ass2.process.OrderProcessPortType;
import at.ac.tuwien.infosys.aic10.ass2.process.OrderProcessService;
import java.util.TimerTask;

/**
 *
 * @author Alex
 */
public class CallbackThread extends TimerTask {

    private String shippingID;
    private static OrderProcessPortType op;



    public CallbackThread(String shippingID)
    {
        OrderProcessService client = new OrderProcessService();
        op = client.getOrderProcessPort();
        this.shippingID = shippingID;
    }

    @Override
    public void run() {
        System.out.println("Invoke Callback with id " + shippingID);
        ParametersCallback parameter = new ParametersCallback();
        parameter.setShippingId(shippingID);
        op.callback(parameter);
    }
}
