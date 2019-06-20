package net.prosavage.savagerpg.command;

import net.prosavage.savagerpg.itembuilder.Item;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Weapon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class getItemCommand implements CommandExecutor {

    Color Color = new Color();
    Item Item = new Item();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            if (args[0].equals("weapon")) {

                ItemStack item = Item.getNewWeapon();
                if (item != null) {
                    player.getInventory().addItem(item);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}