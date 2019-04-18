package net.prosavage.savageequipment.utils;

import net.prosavage.savageequipment.SavageEquipment;

import java.util.Random;

public class Chance {

    public Boolean ofDouble(Double chance) {

        Random r = new Random();
        double nextDouble = r.nextDouble();
        if ((nextDouble * 100) <= chance) {
            SavageEquipment.getInstance().sendConsole(String.valueOf(nextDouble * 100));
            return true;
        }
        return false;
    }
}