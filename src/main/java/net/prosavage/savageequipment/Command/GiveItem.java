package me.kingalteriv.pragmata.Command;

import me.kingalteriv.pragmata.Builder.Armor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItem implements CommandExecutor {

    Armor Armor = new Armor();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player))
        {
            Player player = (Player)sender;
            ItemStack getItem = Armor.getNewArmor();
            ItemStack item = Armor.setInfo(getItem);

            player.sendMessage(String.valueOf(item.getItemMeta()));
            player.sendMessage(String.valueOf(getItem.getItemMeta()));

            player.getInventory().addItem(getItem);
        }
        return true;
    }
}
