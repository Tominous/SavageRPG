package net.prosavage.savagerpg.commands;

import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItem implements CommandExecutor {

    Color Color = new Color();
    Weapon Weapon = new Weapon();
    Armor Armor = new Armor();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player))
        {
            Player player = (Player) sender;
            if (args[0].equals("weapon")){

                ItemStack getItem = Weapon.getNewWeapon();
                ItemStack item = Weapon.setItem(getItem);
                item = Weapon.setLore(item);
                if (item != null) {
                    player.getInventory().addItem(item);
                    return true;
                }
                return false;
            }
            if (args[0].equals("armor")){

                ItemStack getItem = Armor.getNewArmor();
                ItemStack item = Armor.setItem(getItem);
                item = Armor.setLore(item);
                if (item != null) {
                    player.getInventory().addItem(item);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}