package net.prosavage.savageequipment.utils;

import java.util.Random;

public class Chance {

    public Boolean ofDouble(Double chance) {

        Random r = new Random();

        if ((r.nextDouble()) <= chance) {
            return true;
        }
        return false;
    }
}