
package aic2010.helloworld;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UserAdapter extends XmlAdapter<UserImpl, User> {
    @Override
    public UserImpl marshal(User v) throws Exception {
        if (v instanceof UserImpl) {
            return (UserImpl)v;
        }

        UserImpl ui = new UserImpl();
        ui.setName(v.getName());

        return ui;
    }

    @Override
    public User unmarshal(UserImpl v) throws Exception {
        return v;
    }
}

