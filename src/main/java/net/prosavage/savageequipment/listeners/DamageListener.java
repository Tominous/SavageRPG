package net.prosavage.savageequipment.listeners;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Cooldowns;
import net.prosavage.savageequipment.utils.Number;
import net.prosavage.savageequipment.utils.Spawn;
import net.prosavage.savageequipment.values.Weapons;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

    FileConfiguration weaponConfig = SavageEquipment.getInstance().getConfig();
    Color Color = new Color();
    Spawn Spawn = new Spawn();
    Number Number = new Number();
    Weapons Weapons = new Weapons();
    Cooldowns Cooldown = new Cooldowns();

    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player player = (Player) e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            double cooldown = Weapons.getCooldownLore(item);
            double damage = Weapons.getDamageLore(item);
            boolean isOnCooldown = Cooldown.isOnCooldown(player, ((long) cooldown));
            if (!(isOnCooldown)) {
                e.setDamage(damage);
                Spawn.damageIndicator(player, e.getEntity(), String.valueOf(damage));
            }else{
                e.setCancelled(true);
                player.sendMessage("Test, your still on cooldown for " + String.format("%.2f", cooldown - Cooldown.getAttack(player)) + "s");
            }
        }
    }


}
