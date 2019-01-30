package net.prosavage.savageequipment.enchant;

import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CESword implements Listener {

    CustomEnchant CE = new CustomEnchant();
    Color Color = new Color();

    @EventHandler
    public void ceDamageListener(EntityDamageByEntityEvent e){

        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            Integer lore = CE.getLevel(item, "Doublestrike");

            if (item.getType() != Material.AIR) {
                player.sendMessage("1 " + lore);
                if (lore != null) {
                    if (CE.chanceOf(10)) {
                        player.sendMessage(String.valueOf(e.getDamage()));
                        e.setDamage(e.getDamage() * 1.25);
                        player.sendMessage("DOUBLE STRIKED!" + String.valueOf(e.getDamage()));
                    }
                }
            }
        }
    }
}
