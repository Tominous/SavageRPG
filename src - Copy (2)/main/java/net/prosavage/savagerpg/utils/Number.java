package net.prosavage.savagerpg.utils;

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

    public int floor(double number){
        return Integer.parseInt(String.valueOf(Math.floor(number)));
    }

    public boolean isLessThan(int num, int lessThan){
        return num < lessThan;
    }

    public boolean isLessOrEqualTo(int num, int lessThanOrEqual){
        return num <= lessThanOrEqual;
    }

    public boolean isEqualTo(int num, int equal){
        return num == equal;
    }

    public boolean isGreaterOrEqualTo(int num, int greaterOrEqualTo){
        return num >= greaterOrEqualTo;
    }

    public boolean isGreaterThan(int num, int greaterThan){
        return num > greaterThan;
    }

    public boolean isLessThan(double num, double lessThan){
        return num < lessThan;
    }

    public boolean isLessOrEqualTo(double num, double lessThanOrEqual){
        return num <= lessThanOrEqual;
    }

    public boolean isEqualTo(double num, double equal){
        return num == equal;
    }

    public boolean isGreaterOrEqualTo(double num, double greaterOrEqualTo){
        return num >= greaterOrEqualTo;
    }

    public boolean isGreaterThan(double num, double greaterThan){
        return num > greaterThan;
    }
}