package com.lhd.quickindex;

/**
 * Created by lihuaidong on 2017/6/15 16:26.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class Person
{
    private String name;
    private String pinYin;
    public Person()
    {
    }

    public Person(String name)
    {
        this.name = name;
        this.pinYin = PinYinUtils.getPinYin(name);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPinYin()
    {
        return pinYin;
    }

    public void setPinYin(String pinYin)
    {
        this.pinYin = pinYin;
    }

    @Override
    public String toString()
    {
        return "Person{" +
               "name='" + name + '\'' +
               ", pinYin='" + pinYin + '\'' +
               '}';
    }
}
