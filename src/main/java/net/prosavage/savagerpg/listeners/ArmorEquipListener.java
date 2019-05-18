package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorEquipEvent;
import net.prosavage.savagerpg.values.Armors;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ArmorEquipListener implements Listener {

    Armors Armors = new Armors();

    @EventHandler
    public void ArmorChange(ArmorEquipEvent e) {
        new BukkitRunnable() {
            public void run() {
                Player player = e.getPlayer();
                ItemStack playerHelmet = player.getInventory().getHelmet();
                ItemStack playerChestplate = player.getInventory().getChestplate();
                ItemStack playerLeggings = player.getInventory().getLeggings();
                ItemStack playerBoots = player.getInventory().getBoots();

                Double protectionValue = Armors.getArmorProtection(playerHelmet, playerChestplate, playerLeggings, playerBoots);
                Double healthValue = Armors.getArmorHealth(playerHelmet, playerChestplate, playerLeggings, playerBoots);

                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(protectionValue);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20 + healthValue);
            }
        }.runTaskLater(SavageRPG.getInstance(), 1L);
    }
}
