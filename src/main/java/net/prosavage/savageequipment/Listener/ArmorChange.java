package net.prosavage.savageequipment.listener;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import net.prosavage.savageequipment.builder.Armor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import net.prosavage.savageequipment.Main;

public class ArmorChange implements Listener {

    Color Color = new Color();
    Armor Armor = new Armor();

    @EventHandler
    public void ArmorEvent(final ArmorEquipEvent e){
        new BukkitRunnable(){
            public void run(){

                Player player = e.getPlayer();

                ItemStack helmet = player.getInventory().getHelmet();
                ItemStack chestplate = player.getInventory().getChestplate();
                ItemStack leggings = player.getInventory().getLeggings();
                ItemStack boots = player.getInventory().getBoots();

                double helmetProtection = Armor.getProtection(helmet);
                double chestplateProtection = Armor.getProtection(chestplate);
                double leggingsProtection = Armor.getProtection(leggings);
                double bootsProtection = Armor.getProtection(boots);

                double removeHelmetProtection = Armor.getDefaultProtection(helmet);
                double removeChestplateProtection = Armor.getDefaultProtection(chestplate);
                double removeLeggingsProtection = Armor.getDefaultProtection(leggings);
                double removeBootsProtection = Armor.getDefaultProtection(boots);

                double helmetHealth = Armor.getHealth(helmet);
                double chestplateHealth = Armor.getHealth(chestplate);
                double leggingsHealth = Armor.getHealth(leggings);
                double bootsHealth = Armor.getHealth(boots);

                double protectionValue = helmetProtection + chestplateProtection + leggingsProtection + bootsProtection;
                double removeValue = removeHelmetProtection + removeChestplateProtection + removeLeggingsProtection + removeBootsProtection;

                double healthValue = helmetHealth + chestplateHealth + leggingsHealth + bootsHealth;

                player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue((removeValue * -1) + protectionValue);
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + healthValue);

            }
        }.runTaskLater(Main.getInstance(), 1L);

    }

}
