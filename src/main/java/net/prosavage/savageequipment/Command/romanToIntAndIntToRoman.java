package net.prosavage.savageequipment.command;

import net.prosavage.savageequipment.enchant.CustomEnchant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class romanToIntAndIntToRoman implements CommandExecutor {

    CustomEnchant CE = new CustomEnchant();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player))
        {
            Player player = (Player)sender;

            if (isInteger(args[0])){

                String romanToNumeral = CE.intToRoman(Integer.valueOf(args[0]));

                Integer numeralToRoman = CE.romanToInt(romanToNumeral);

                player.sendMessage(romanToNumeral + " " + numeralToRoman);

            }

        }
        return true;
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
