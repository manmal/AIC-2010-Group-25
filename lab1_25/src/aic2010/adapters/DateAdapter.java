package aic2010.adapters;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<Long, Date> {

    @Override
    public Long marshal(Date v) throws Exception
    {
        return v.getTime();
    }

    @Override
    public Date unmarshal(Long v) throws Exception
    {
        return new Date(v);
    }
}
