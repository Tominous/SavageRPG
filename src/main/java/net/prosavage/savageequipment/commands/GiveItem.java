package net.prosavage.savageequipment.commands;

import net.prosavage.savageequipment.itembuilder.Armor;
import net.prosavage.savageequipment.itembuilder.Weapon;
import net.prosavage.savageequipment.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItem implements CommandExecutor {

    Armor Armor = new Armor();
    Color Color = new Color();
    Weapon Weapon = new Weapon();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player))
        {
            Player player = (Player) sender;
            if (args[0].equals("armor")) {
                ItemStack getItem = Armor.getNewArmor();
                ItemStack item = Armor.setItem(getItem);
                player.getInventory().addItem(item);
                return true;
            }
            else if (args[0].equals("weapon")){

                ItemStack getItem = Weapon.getNewWeapon();
                ItemStack item = Weapon.setItem(getItem);
                player.getInventory().addItem(item);
                return true;
            }
        }
        return false;
    }
}