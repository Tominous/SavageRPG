package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.PDCPlayer;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorEquipEvent;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ArmorEquipListener implements Listener {

    Armor Armor = new Armor();
    Number Number = new Number();

    @EventHandler
    public void armorEquip(ArmorEquipEvent e){
        new BukkitRunnable(){
            @Override
            public void run() {
                Player player = e.getPlayer();
                ItemStack helmet = player.getInventory().getHelmet();
                ItemStack chestplate = player.getInventory().getChestplate();
                ItemStack leggings = player.getInventory().getLeggings();
                ItemStack boots = player.getInventory().getBoots();

                assert helmet != null;
                assert chestplate != null;
                assert leggings != null;
                assert boots != null;

                double helmetHealth = Armor.getHealth(helmet);
                double chestplateHealth = Armor.getHealth(chestplate);
                double leggingsHealth = Armor.getHealth(leggings);
                double bootsHealth = Armor.getHealth(boots);

                double health = helmetHealth + chestplateHealth + leggingsHealth + bootsHealth;
                PDCPlayer pdcPlayer = new PDCPlayer(player);
                pdcPlayer.updateDefense();
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20 + health);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(0);
            }
        }.runTaskLaterAsynchronously(SavageRPG.getInstance(), 1);

    }

}
