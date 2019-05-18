package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.SavageRPG;

import java.util.Random;

public class Chance {

    public Boolean ofDouble(Double chance) {

        Random r = new Random();
        double nextDouble = r.nextDouble();
        if ((nextDouble * 100) <= chance) {
            SavageRPG.getInstance().sendConsole(String.valueOf(nextDouble * 100));
            return true;
        }
        return false;
    }
}