package net.prosavage.savagerpg.utils;

import java.util.Random;

public class Chance {

    public Boolean ofDouble(Double chance) {

        Random r = new Random();
        double nextDouble = r.nextDouble();
        if ((nextDouble * 100) <= chance) {
            return true;
        }
        return false;
    }
}