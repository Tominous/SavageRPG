package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ArmorEquipListener implements Listener {

    Armor Armor = new Armor();

    @EventHandler
    public void ArmorChange(ArmorEquipEvent e) {
        new BukkitRunnable() {
            public void run() {
                Player player = e.getPlayer();
                ItemStack playerHelmet = player.getInventory().getHelmet();
                ItemStack playerChestplate = player.getInventory().getChestplate();
                ItemStack playerLeggings = player.getInventory().getLeggings();
                ItemStack playerBoots = player.getInventory().getBoots();

                Double protectionValue = Armor.getProtection(playerHelmet) + Armor.getProtection(playerChestplate) + Armor.getProtection(playerLeggings) + Armor.getProtection(playerBoots);
                Double healthValue = Armor.getHealth(playerHelmet) + Armor.getHealth(playerChestplate) + Armor.getHealth(playerLeggings) + Armor.getHealth(playerBoots);

                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(protectionValue);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20 + healthValue);
            }
        }.runTaskLater(SavageRPG.getInstance(), 1L);
    }
}
