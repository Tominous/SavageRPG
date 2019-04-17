package net.prosavage.savageequipment.utils;

import java.util.Random;

public class Number {

    public Double getDouble(Double min, Double max)
    {
        Random r = new Random();
        double num = Double.parseDouble(String.valueOf((min + (max - min) * r.nextDouble())));
        return num;
    }

    public Integer getInteger(Integer min, Integer max)
    {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

}
