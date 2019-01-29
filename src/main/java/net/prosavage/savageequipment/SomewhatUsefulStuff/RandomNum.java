package me.kingalteriv.pragmata.SomewhatUsefulStuff;

import java.util.Random;

public class RandomNum
{
    public static Double getDouble(Double min, Double max)
    {
        Random r = new Random();
        return Double.valueOf(min.intValue() + (max.intValue() - min.intValue()) * r.nextDouble());
    }

    public static Integer getInteger(Integer min, Integer max)
    {
        Random r = new Random();
        return Integer.valueOf(r.nextInt(max.intValue() - min.intValue() + 1) + min.intValue());
    }

}
