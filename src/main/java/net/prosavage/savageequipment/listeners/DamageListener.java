package net.prosavage.savageequipment.listeners;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Spawn;
import net.prosavage.savageequipment.utils.Number;
import net.prosavage.savageequipment.utils.Weapons;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DamageListener implements Listener {

    FileConfiguration weaponConfig = SavageEquipment.getInstance().getConfig();
    Color Color = new Color();
    Spawn Spawn = new Spawn();
    Number Number = new Number();
    Weapons Weapons = new Weapons();

    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player player = (Player) e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            double damage = Weapons.getDamageLore(item);
            e.setDamage(damage);
            Spawn.damageIndicator(player, e.getEntity(), String.valueOf(damage));
        }
    }


}
