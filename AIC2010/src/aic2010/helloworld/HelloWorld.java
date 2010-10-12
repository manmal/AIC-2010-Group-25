package aic2010.helloworld;

import java.util.Map;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface HelloWorld {

    String sayHi(@WebParam(name="text") String text);

    
    //@XmlJavaTypeAdapter(UserAdapter.class)
    String sayHiToUser(@WebParam(name="user") User user);

    @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
    Map<Integer, User> getUsers();
}
