package aic2010.helloworld;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UserType")
public class UserImpl implements User {
    String name;

    public UserImpl() {
    }
    
    public UserImpl(String s) {
        name = s;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }
}

