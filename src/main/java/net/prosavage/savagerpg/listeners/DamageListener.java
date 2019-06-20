package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.packets.Actionbar;
import net.prosavage.savagerpg.utils.*;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class DamageListener implements Listener {

    FileConfiguration weaponConfig = SavageRPG.getInstance().getConfig();
    Color Color = new Color();
    Spawn Spawn = new Spawn();
    Number Number = new Number();
    Weapon Weapon = new Weapon();
    Cooldowns Cooldown = new Cooldowns();
    SRNamespacedKey SRNamespacedKey = new SRNamespacedKey();
    Actionbar Actionbar = new Actionbar();

    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player player = (Player) e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            double cooldown = Weapon.getCooldown(item);
            double minDamage = Weapon.getMinDamage(item);
            double maxDamage = Weapon.getMaxDamage(item);
            double damage = Number.getDouble(minDamage, maxDamage);
            player.getPersistentDataContainer().set(SRNamespacedKey.getNamespacedKey_player_recent_damage(), PersistentDataType.DOUBLE, damage);
            if (!(SavageRPG.getInstance().getConfig().getBoolean("features.cooldown"))){
                e.setDamage(damage);
                Spawn.damageIndicator(player, e.getEntity(), String.valueOf(damage));
            }else if (SavageRPG.getInstance().getConfig().getBoolean("features.cooldown")) {
                boolean isOnCooldown = Cooldown.isOnCooldown(player, ((long) cooldown));
                if (!(isOnCooldown)) {
                    e.setDamage(damage);
                    Spawn.damageIndicator(player, e.getEntity(), String.valueOf(damage));
                } else {
                    e.setCancelled(true);
                    Actionbar.sendMessage(player, "Your still on cooldown for " + String.format("%.2f", cooldown - Cooldown.getAttack(player)) + "s");
                }
            }
        }
    }


}
