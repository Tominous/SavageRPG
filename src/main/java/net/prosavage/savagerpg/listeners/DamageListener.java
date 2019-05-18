package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Cooldowns;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Spawn;
import net.prosavage.savagerpg.values.Weapons;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

    FileConfiguration weaponConfig = SavageRPG.getInstance().getConfig();
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
