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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserImpl other = (UserImpl) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }


}

